package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.PartidoArbitro;
import com.codigoartesanal.entuliga.model.TipoArbitro;
import com.codigoartesanal.entuliga.model.TorneoArbitro;
import com.codigoartesanal.entuliga.repositories.PartidoArbitroRepository;
import com.codigoartesanal.entuliga.services.PartidoArbitroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adan on 29/11/17.
 */
@Service
public class PartidoArbitroServiceImpl implements PartidoArbitroService {

    @Autowired
    PartidoArbitroRepository partidoArbitroRepository;

    @Override
    public Map<String, Object> createPartidoArbitro(Map<String, String> mapPartidoArbitro) {
        PartidoArbitro partidoArbitro = converMapToPartidoArbitro(mapPartidoArbitro);
        System.out.println("createPartidoArbitro Implementacion del servicio");
        return convertPartidoArbitroToMap(partidoArbitroRepository.save(partidoArbitro));
    }

    private PartidoArbitro converMapToPartidoArbitro(Map<String,String> mapPartidoArbitro){
        System.out.println("convertir el mapa a objeto arbitro partido");

        PartidoArbitro partidoArbitro = new PartidoArbitro();
        if (mapPartidoArbitro.containsKey(PROPERTY_ID)){
            partidoArbitro = this.getPartidoArbitro(Long.valueOf(mapPartidoArbitro.get(PROPERTY_ID)));
        }
        Partido partido = new Partido();
        partido.setId(Long.valueOf(mapPartidoArbitro.get(PROPERTY_PARTIDO_ID)));

        TorneoArbitro torneoArbitro = new TorneoArbitro();
        torneoArbitro.setId(Long.valueOf(mapPartidoArbitro.get(PROPERTY_TORNEO_ARBITRO_ID)));

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
        return map;
    }

    private PartidoArbitro getPartidoArbitro(Long idPartidoArbitro){
        return this.partidoArbitroRepository.findById(idPartidoArbitro).get();
    }



}
