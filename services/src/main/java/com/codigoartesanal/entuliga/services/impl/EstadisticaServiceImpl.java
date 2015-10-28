package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;
import com.codigoartesanal.entuliga.repositories.TorneoRepository;
import com.codigoartesanal.entuliga.services.EstadisticaService;
import com.codigoartesanal.entuliga.services.PhotoService;
import com.codigoartesanal.entuliga.services.TipoPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by betuzo on 22/10/15.
 */
@Service
public class EstadisticaServiceImpl implements EstadisticaService{

    @Autowired
    TorneoRepository torneoRepository;

    @Autowired
    PhotoService photoService;

    @Override
    public List<EstadisticaJugadorDTO> listLideresTodosByTorneoTopFive(Long idTorneo) {
        List<EstadisticaJugadorDTO> estadisticasTodas = new ArrayList<>();
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Pageable pageable = createPageableByLimit(5);

        estadisticasTodas.addAll(torneoRepository.findLimitLideresPuntosByTorneo(torneo, pageable));
        estadisticasTodas.addAll(torneoRepository.findLimitLideresRebotesByTorneo(torneo, pageable));
        estadisticasTodas.addAll(torneoRepository.findLimitLideresAsistenciasByTorneo(torneo, pageable));
        estadisticasTodas.addAll(torneoRepository.findLimitLideresBloqueosByTorneo(torneo, pageable));
        estadisticasTodas.addAll(torneoRepository.findLimitLideresRobosByTorneo(torneo, pageable));

        for(EstadisticaJugadorDTO dto : estadisticasTodas){
            dto.setFotoJugador(photoService.getValidPath(dto.getFotoJugador(), TipoPhoto.JUGADOR));
            dto.setLogoEquipo(photoService.getValidPath(dto.getLogoEquipo(), TipoPhoto.EQUIPO));
        }

        return estadisticasTodas;
    }

    private Pageable createPageableByLimit(int limit){
        return new PageRequest(0, limit);
    }

}
