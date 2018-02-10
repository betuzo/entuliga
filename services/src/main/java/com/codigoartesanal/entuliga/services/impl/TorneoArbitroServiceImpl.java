package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Arbitro;
import com.codigoartesanal.entuliga.model.StatusArbitro;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoArbitro;
import com.codigoartesanal.entuliga.repositories.*;
import com.codigoartesanal.entuliga.services.TorneoArbitroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 17/06/16.
 */
@Service
public class TorneoArbitroServiceImpl implements TorneoArbitroService {

    @Autowired
    TorneoArbitroRepository torneoArbitroRepository;

    @Autowired
    ArbitroRepository arbitroRepository;

    @Autowired
    TorneoRepository torneoRepository;

    @Override
    public Map<String, Object> createTorneoArbitro(Map<String, String> mapTorneoArbitro) {
        TorneoArbitro torneoArbitro = convertMapToTorneoArbitro(mapTorneoArbitro);
        torneoArbitro = torneoArbitroRepository.save(torneoArbitro);
        torneoArbitro.setArbitro(arbitroRepository.findById(torneoArbitro.getArbitro().getId()).get());
        torneoArbitro.setTorneo(torneoRepository.findById(torneoArbitro.getTorneo().getId()).get());
        return convertTorneoArbitroToMap(torneoArbitro);
    }

    @Override
    public DeleteStatusEnum deleteTorneoArbitro(Long idTorneoArbitro) {
        try {
            torneoArbitroRepository.deleteById(idTorneoArbitro);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    @Override
    public List<Map<String, Object>> listArbitroByTorneo(Long idTorneo) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<TorneoArbitro> itTorneoArbitro = torneoArbitroRepository.findAllByTorneo(torneo).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneoArbitro.hasNext()) {
            TorneoArbitro torneoArbitro = itTorneoArbitro.next();
            Map<String, Object> dto = convertTorneoArbitroToMap(torneoArbitro);
            copy.add(dto);
        }
        return copy;
    }

    private TorneoArbitro convertMapToTorneoArbitro(Map<String, String> torneoArbitroMap) {
        TorneoArbitro torneoArbitro = new TorneoArbitro();
        Arbitro arbitro = new Arbitro();
        arbitro.setId(Long.valueOf(torneoArbitroMap.get(PROPERTY_ARBITRO_ID)));

        torneoArbitro.setArbitro(arbitro);

        Torneo torneo = new Torneo();
        torneo.setId(Long.valueOf(torneoArbitroMap.get(PROPERTY_TORNEO_ID)));

        torneoArbitro.setTorneo(torneo);
        torneoArbitro.setStatusArbitro(StatusArbitro.valueOf(torneoArbitroMap.get(PROPERTY_STATUS_ARBITRO)));

        return torneoArbitro;
    }

    private Map<String, Object> convertTorneoArbitroToMap(TorneoArbitro torneoArbitro) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, torneoArbitro.getId());
        map.put(PROPERTY_ARBITRO_ID, torneoArbitro.getArbitro().getId());
        map.put(PROPERTY_ARBITRO_NOMBRE, torneoArbitro.getArbitro().getNombre());
        map.put(PROPERTY_ARBITRO_PATERNO, torneoArbitro.getArbitro().getPaterno());
        map.put(PROPERTY_ARBITRO_MATERNO, torneoArbitro.getArbitro().getMaterno());
        map.put(PROPERTY_TORNEO_ID, torneoArbitro.getTorneo().getId());
        map.put(PROPERTY_TORNEO_NOMBRE, torneoArbitro.getTorneo().getNombre());
        map.put(PROPERTY_STATUS_ARBITRO, torneoArbitro.getStatusArbitro());
        return map;
    }
}
