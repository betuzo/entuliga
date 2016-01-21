package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Fase;
import com.codigoartesanal.entuliga.model.Jornada;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.repositories.JornadaRepository;
import com.codigoartesanal.entuliga.repositories.TorneoRepository;
import com.codigoartesanal.entuliga.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 06/06/15.
 */
@Service
public class JornadaServiceImpl implements JornadaService {

    @Autowired
    JornadaRepository jornadaRepository;

    @Autowired
    TorneoRepository torneoRepository;

    @Override
    public List<Map<String, Object>> listJornadaByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<Jornada> itJornada = jornadaRepository.findAllByTorneo(torneo).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itJornada.hasNext()) {
            Jornada jornada = itJornada.next();
            Map<String, Object> dto = convertJornadaToMap(jornada);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public Map<String, Object> createJornada(Map<String, String> jornadaMap) {
        Jornada jornada = convertMapToJornada(jornadaMap);
        jornada = jornadaRepository.save(jornada);
        jornada.setTorneo(torneoRepository.findOne(jornada.getTorneo().getId()));
        return convertJornadaToMap(jornada);
    }

    @Override
    public DeleteStatusEnum deleteJornada(Long idJornada) {
        try {
            jornadaRepository.delete(idJornada);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;

    }

    private Jornada convertMapToJornada(Map<String, String> jornadaMap) {
        Jornada jornada = new Jornada();

        Torneo torneo = new Torneo();
        torneo.setId(Long.valueOf(jornadaMap.get(PROPERTY_TORNEO_ID)));

        jornada.setTorneo(torneo);
        jornada.setNombre(jornadaMap.get(PROPERTY_NOMBRE));
        jornada.setFase(Fase.valueOf(jornadaMap.get(PROPERTY_FASE)));

        return jornada;
    }

    private Map<String, Object> convertJornadaToMap(Jornada jornada) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, jornada.getId());
        map.put(PROPERTY_TORNEO_ID, jornada.getTorneo().getId());
        map.put(PROPERTY_TORNEO_NOMBRE, jornada.getTorneo().getNombre());
        map.put(PROPERTY_NOMBRE, jornada.getNombre());
        map.put(PROPERTY_FASE, jornada.getFase());
        return map;
    }
}

