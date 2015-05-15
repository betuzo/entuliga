package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Colonia;
import com.codigoartesanal.entuliga.model.Estado;
import com.codigoartesanal.entuliga.model.Municipio;
import com.codigoartesanal.entuliga.repositories.ColoniaRepository;
import com.codigoartesanal.entuliga.services.ColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 14/05/15.
 */
@Service
public class ColoniaServiceImpl implements ColoniaService {

    @Autowired
    ColoniaRepository coloniaRepository;

    @Override
    public List<Map<String, Object>> listColoniaByMunicipio(Long idMunicipio) {
        Municipio municipio = new Municipio();
        municipio.setId(idMunicipio);
        Iterator<Colonia> itColonia = coloniaRepository.findAllByMunicipio(municipio).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itColonia.hasNext()) {
            Colonia colonia = itColonia.next();
            Map<String, Object> dto = convertColoniaToMap(colonia);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertColoniaToMap(Colonia colonia) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, colonia.getId());
        map.put(PROPERTY_NOMBRE, colonia.getNombre());
        map.put(PROPERTY_ID_MUNICIPIO, colonia.getMunicipio().getId());
        return map;
    }
}
