package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.PartidoArbitroRepository;
import com.codigoartesanal.entuliga.services.PartidoArbitroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by adan on 29/11/17.
 */
@Service
public class PartidoArbitroServiceImpl implements PartidoArbitroService {

    @Autowired
    PartidoArbitroRepository partidoArbitroRepository;

    @Override
    public Map<String, Object> createPartidoArbitro(Map<String, String> mapPartidoArbitro) {
        PartidoArbitro partidoArbitro = convertMapToPartidoArbitro(mapPartidoArbitro);
        return convertPartidoArbitroToMap(partidoArbitroRepository.save(partidoArbitro));
    }

    @Override
    public List<Map<String, Object>> arbitrosByPartido(Long idPartido) {
        Partido partido = new Partido();
        partido.setId(idPartido);
        Iterator<PartidoArbitro> partidoArbitroIterator = partidoArbitroRepository.findAllByPartido(partido).iterator();
        List<Map<String, Object>> listPartidoArbitro = new ArrayList<>();
        while (partidoArbitroIterator.hasNext()){
            PartidoArbitro arbitro = partidoArbitroIterator.next();
            Map<String, Object> dto = convertPartidoArbitroToMap(arbitro);
            listPartidoArbitro.add(dto);
        }
        return listPartidoArbitro;
    }



    private PartidoArbitro convertMapToPartidoArbitro(Map<String,String> mapPartidoArbitro){
        PartidoArbitro partidoArbitro = new PartidoArbitro();
        if (mapPartidoArbitro.containsKey(PROPERTY_ID)){
            partidoArbitro = this.getPartidoArbitro(Long.valueOf(mapPartidoArbitro.get(PROPERTY_ID)));
        }
        Partido partido = new Partido();
        partido.setId(Long.valueOf(mapPartidoArbitro.get(PROPERTY_PARTIDO_ID)));

        Arbitro arbitro = new Arbitro();
        arbitro.setId(Long.valueOf(mapPartidoArbitro.get(PROPERTY_ARBITRO_ID)));
        arbitro.setNombre(mapPartidoArbitro.get(PROPERTY_NOMBRE_ARBITRO));
        arbitro.setPaterno(mapPartidoArbitro.get(PROPERTY_PATERNO_ARBITRO));
        arbitro.setMaterno(mapPartidoArbitro.get(PROPERTY_MATERNO_ARBITRO));

        TorneoArbitro torneoArbitro = new TorneoArbitro();
        torneoArbitro.setId(Long.valueOf(mapPartidoArbitro.get(PROPERTY_TORNEO_ARBITRO_ID)));
        torneoArbitro.setArbitro(arbitro);

        partidoArbitro.setPartido(partido);
        partidoArbitro.setArbitro(torneoArbitro);
        partidoArbitro.setTipoArbitro(TipoArbitro.valueOf(mapPartidoArbitro.get(PROPERTY_TIPO_ARBITRO)));


        return partidoArbitro;
    }

    private Map<String,Object> convertPartidoArbitroToMap(PartidoArbitro partidoArbitro){
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, partidoArbitro.getId());
        map.put(PROPERTY_TIPO_ARBITRO, partidoArbitro.getTipoArbitro());
        map.put(PROPERTY_PARTIDO_ID, partidoArbitro.getPartido().getId());
        map.put(PROPERTY_TORNEO_ARBITRO_ID, partidoArbitro.getArbitro().getId());
        if (partidoArbitro.getArbitro().getArbitro() != null){
            map.put(PROPERTY_NOMBRE_ARBITRO, partidoArbitro.getArbitro().getArbitro().getNombre());
            map.put(PROPERTY_MATERNO_ARBITRO, partidoArbitro.getArbitro().getArbitro().getMaterno());
            map.put(PROPERTY_PATERNO_ARBITRO, partidoArbitro.getArbitro().getArbitro().getPaterno());
        }
        return map;
    }

    private PartidoArbitro getPartidoArbitro(Long idPartidoArbitro){
        return this.partidoArbitroRepository.findById(idPartidoArbitro).get();
    }



}
