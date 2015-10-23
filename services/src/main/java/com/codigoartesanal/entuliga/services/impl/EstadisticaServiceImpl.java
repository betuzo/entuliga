package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;
import com.codigoartesanal.entuliga.repositories.TorneoRepository;
import com.codigoartesanal.entuliga.services.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by betuzo on 22/10/15.
 */
@Service
public class EstadisticaServiceImpl implements EstadisticaService{

    @Autowired
    TorneoRepository torneoRepository;

    @Override
    public List<EstadisticaJugadorDTO> listLideresPuntosByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        return torneoRepository.findAllLideresPuntosByTorneo(torneo);
    }

    @Override
    public List<EstadisticaJugadorDTO> listLideresRebotesByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        return torneoRepository.findAllLideresRebotesByTorneo(torneo);
    }

    @Override
    public List<EstadisticaJugadorDTO> listLideresAsistenciasByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        return torneoRepository.findAllLideresAsistenciasByTorneo(torneo);
    }

    @Override
    public List<EstadisticaJugadorDTO> listLideresBloqueosByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        return torneoRepository.findAllLideresBloqueosByTorneo(torneo);
    }

    @Override
    public List<EstadisticaJugadorDTO> listLideresRobosByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        return torneoRepository.findAllLideresRobosByTorneo(torneo);
    }

}
