package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.EquipoRepository;
import com.codigoartesanal.entuliga.repositories.JornadaRepository;
import com.codigoartesanal.entuliga.repositories.TorneoEquipoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoRepository;
import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 1/06/15.
 */
@Service
public class TorneoEquipoServiceImpl implements TorneoEquipoService {

    @Autowired
    TorneoEquipoRepository torneoEquipoRepository;

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    TorneoRepository torneoRepository;

    @Autowired
    JornadaRepository jornadaRepository;

    @Override
    public Map<String, Object> createTorneoEquipo(Map<String, String> equipo) {
        TorneoEquipo torneoEquipo = convertMapToTorneoEquipo(equipo);
        torneoEquipo = torneoEquipoRepository.save(torneoEquipo);
        torneoEquipo.setEquipo(equipoRepository.findOne(torneoEquipo.getEquipo().getId()));
        torneoEquipo.setTorneo(torneoRepository.findOne(torneoEquipo.getTorneo().getId()));
        return convertTorneoEquipoToMap(torneoEquipo);
    }

    @Override
    public List<Map<String, Object>> listTorneoEquipoByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<TorneoEquipo> itTorneoEquipo = torneoEquipoRepository.findAllByTorneo(torneo).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneoEquipo.hasNext()) {
            TorneoEquipo torneoEquipo = itTorneoEquipo.next();
            Map<String, Object> dto = convertTorneoEquipoToMap(torneoEquipo);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public DeleteStatusEnum deleteTorneoEquipo(Long idTorneoEquipo) {
        try {
            torneoEquipoRepository.delete(idTorneoEquipo);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    @Override
    public List<Map<String, Object>> listTorneoEquipoByJornada(Long idJornada) {
        Jornada jornada = jornadaRepository.findOne(idJornada);
        Iterator<TorneoEquipo> itTorneoEquipo = torneoEquipoRepository.findAllByTorneoNotInJornada(jornada.getTorneo(), jornada).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneoEquipo.hasNext()) {
            TorneoEquipo torneoEquipo = itTorneoEquipo.next();
            Map<String, Object> dto = convertTorneoEquipoToMap(torneoEquipo);
            copy.add(dto);
        }
        return copy;
    }

    private TorneoEquipo convertMapToTorneoEquipo(Map<String, String> torneoEquipoMap) {
        TorneoEquipo torneoEquipo = new TorneoEquipo();
        Equipo equipo = new Equipo();
        equipo.setId(Long.valueOf(torneoEquipoMap.get(PROPERTY_EQUIPO_ID)));

        torneoEquipo.setEquipo(equipo);

        Torneo torneo = new Torneo();
        torneo.setId(Long.valueOf(torneoEquipoMap.get(PROPERTY_TORNEO_ID)));

        torneoEquipo.setTorneo(torneo);
        torneoEquipo.setStatusEquipo(StatusEquipo.valueOf(torneoEquipoMap.get(PROPERTY_STATUS_EQUIPO)));

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
