package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.view.Clasificacion;
import com.codigoartesanal.entuliga.repositories.view.ClasificacionRepository;
import com.codigoartesanal.entuliga.services.ClasificacionService;
import com.codigoartesanal.entuliga.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 21/10/15.
 */
@Service
public class ClasificacionServiceImpl implements ClasificacionService {

    @Autowired
    ClasificacionRepository clasificacionRepository;

    @Autowired
    PhotoService photoService;

    @Override
    public List<Map<String, Object>> listClasificacionByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<Clasificacion> itClasificacion = clasificacionRepository.findAllByTorneoOrderByTotalGanadosDescDiferenciaPuntosDesc(torneo).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itClasificacion.hasNext()) {
            Clasificacion clasificacion = itClasificacion.next();
            Map<String, Object> dto = convertClasificacionToMap(clasificacion);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertClasificacionToMap(Clasificacion clasificacion) {
        Map<String, Object> map = new HashMap<>();

        map.put(PROPERTY_EQUIPO_ID, clasificacion.getEquipo().getId());
        map.put(PROPERTY_EQUIPO_NOMBRE, clasificacion.getEquipo().getEquipo().getNombre());
        map.put(PROPERTY_EQUIPO_LOGO,
                photoService.getValidPathWebLogo(clasificacion.getEquipo().getEquipo().getRutaLogoEquipo(), null));
        map.put(PROPERTY_TORNEO_ID, clasificacion.getTorneo().getId());


        map.put(PROPERTY_PUNTOS_LOCAL, clasificacion.getPuntosLocal());
        map.put(PROPERTY_PUNTOS_LOCAL_CONTRA, clasificacion.getPuntosLocalContra());
        map.put(PROPERTY_GANADOS_LOCAL, clasificacion.getGanadosLocal());
        map.put(PROPERTY_PERDIDOS_LOCAL, clasificacion.getPerdidosLocal());
        map.put(PROPERTY_JUGADOS_LOCAL, clasificacion.getJugadosLocal());

        map.put(PROPERTY_PUNTOS_VISITA, clasificacion.getPuntosVisita());
        map.put(PROPERTY_PUNTOS_VISITA_CONTRA, clasificacion.getPuntosVisitaContra());
        map.put(PROPERTY_GANADOS_VISITA, clasificacion.getGanadosVisita());
        map.put(PROPERTY_PERDIDOS_VISITA, clasificacion.getPerdidosVisita());
        map.put(PROPERTY_JUGADOS_VISITA, clasificacion.getJugadosVisita());

        map.put(PROPERTY_TOTAL_PUNTOS_FAVOR, clasificacion.getTotalPuntosFavor());
        map.put(PROPERTY_TOTAL_PUNTOS_CONTRA, clasificacion.getTotalPuntosContra());
        map.put(PROPERTY_DIF_PUNTOS, clasificacion.getDiferenciaPuntos());
        map.put(PROPERTY_TOTAL_GANADOS, clasificacion.getTotalGanados());
        map.put(PROPERTY_TOTAL_PERDIDOS, clasificacion.getTotalPerdidos());
        map.put(PROPERTY_TOTAL_JUGADOS, clasificacion.getTotalJugados());

        return map;
    }
}
