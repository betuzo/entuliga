package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Pais;
import com.codigoartesanal.entuliga.repositories.PaisRepository;
import com.codigoartesanal.entuliga.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 14/05/15.
 */
@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    PaisRepository paisRepository;

    @Override
    public List<Map<String, Object>> listPais() {
        Iterator<Pais> itPais = paisRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itPais.hasNext()) {
            Pais pais = itPais.next();
            Map<String, Object> dto = convertPaisToMap(pais);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertPaisToMap(Pais pais) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, pais.getId());
        map.put(PROPERTY_NOMBRE, pais.getNombre());
        map.put(PROPERTY_ABREVIATURA, pais.getAbreviatura());
        return map;
    }
}
