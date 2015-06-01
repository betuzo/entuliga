package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.StatusEquipo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoEquipo;
import com.codigoartesanal.entuliga.repositories.TorneoEquipoRepository;
import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 1/06/15.
 */
@Service
public class TorneoEquipoServiceImpl implements TorneoEquipoService {

    @Autowired
    TorneoEquipoRepository torneoEquipoRepository;

    @Override
    public Map<String, Object> createTorneoEquipo(Long idTorneo, Map<String, String> equipo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        TorneoEquipo torneoEquipo = convertMapToTorneoEquipo(equipo);
        torneoEquipo.setTorneo(torneo);
        torneoEquipo.setStatusEquipo(StatusEquipo.INSCRITO);
        return convertTorneoEquipoToMap(torneoEquipoRepository.save(torneoEquipo));
    }

    private TorneoEquipo convertMapToTorneoEquipo(Map<String, String> torneoEquipoMap) {
        TorneoEquipo torneoEquipo = new TorneoEquipo();
        Equipo equipo = new Equipo();
        equipo.setId(Long.valueOf(torneoEquipoMap.get(PROPERTY_EQUIPO_ID)));

        torneoEquipo.setEquipo(equipo);

        return torneoEquipo;
    }

    private Map<String, Object> convertTorneoEquipoToMap(TorneoEquipo torneoEquipo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, torneoEquipo.getId());
        map.put(PROPERTY_EQUIPO_ID, torneoEquipo.getEquipo().getId());
        map.put(PROPERTY_EQUIPO_NOMBRE, torneoEquipo.getEquipo().getNombre());
        map.put(PROPERTY_TORNEO_ID, torneoEquipo.getTorneo().getId());
        map.put(PROPERTY_TORNEO_NOMBRE, torneoEquipo.getTorneo().getNombre());
        map.put(PROPERTY_STATUS_EQUIPO, torneoEquipo.getStatusEquipo());
        return map;
    }
}
