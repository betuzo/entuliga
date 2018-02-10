package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Cancha;
import com.codigoartesanal.entuliga.model.StatusCancha;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoCancha;
import com.codigoartesanal.entuliga.repositories.CanchaRepository;
import com.codigoartesanal.entuliga.repositories.TorneoCanchaRepository;
import com.codigoartesanal.entuliga.repositories.TorneoRepository;
import com.codigoartesanal.entuliga.services.TorneoCanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 25/08/15.
 */
@Service
public class TorneoCanchaServiceImpl implements TorneoCanchaService {


    @Autowired
    TorneoCanchaRepository torneoCanchaRepository;

    @Autowired
    CanchaRepository canchaRepository;

    @Autowired
    TorneoRepository torneoRepository;

    @Override
    public Map<String, Object> createTorneoCancha(Map<String, String> mapTorneoCancha) {
        TorneoCancha torneoCancha = convertMapToTorneoCancha(mapTorneoCancha);
        torneoCancha = torneoCanchaRepository.save(torneoCancha);
        torneoCancha.setCancha(canchaRepository.findById(torneoCancha.getCancha().getId()).get());
        torneoCancha.setTorneo(torneoRepository.findById(torneoCancha.getTorneo().getId()).get());
        return convertTorneoCanchaToMap(torneoCancha);
    }

    @Override
    public List<Map<String, Object>> listCanchaByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<TorneoCancha> itTorneoCancha = torneoCanchaRepository.findAllByTorneo(torneo).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneoCancha.hasNext()) {
            TorneoCancha torneoCancha = itTorneoCancha.next();
            Map<String, Object> dto = convertTorneoCanchaToMap(torneoCancha);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public DeleteStatusEnum deleteTorneoCancha(Long idTorneoCancha) {
        try {
            torneoCanchaRepository.deleteById(idTorneoCancha);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    private TorneoCancha convertMapToTorneoCancha(Map<String, String> torneoEquipoMap) {
        TorneoCancha torneoCancha = new TorneoCancha();
        Cancha cancha = new Cancha();
        cancha.setId(Long.valueOf(torneoEquipoMap.get(PROPERTY_CANCHA_ID)));

        torneoCancha.setCancha(cancha);

        Torneo torneo = new Torneo();
        torneo.setId(Long.valueOf(torneoEquipoMap.get(PROPERTY_TORNEO_ID)));

        torneoCancha.setTorneo(torneo);
        torneoCancha.setStatusCancha(StatusCancha.valueOf(torneoEquipoMap.get(PROPERTY_STATUS_CANCHA)));

        return torneoCancha;
    }

    private Map<String, Object> convertTorneoCanchaToMap(TorneoCancha torneoCancha) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, torneoCancha.getId());
        map.put(PROPERTY_CANCHA_ID, torneoCancha.getCancha().getId());
        map.put(PROPERTY_CANCHA_NOMBRE, torneoCancha.getCancha().getNombre());
        map.put(PROPERTY_TORNEO_ID, torneoCancha.getTorneo().getId());
        map.put(PROPERTY_TORNEO_NOMBRE, torneoCancha.getTorneo().getNombre());
        map.put(PROPERTY_STATUS_CANCHA, torneoCancha.getStatusCancha());
        return map;
    }
}
