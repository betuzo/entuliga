package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Estado;
import com.codigoartesanal.entuliga.model.Municipio;
import com.codigoartesanal.entuliga.model.Pais;
import com.codigoartesanal.entuliga.repositories.MunicipioRepository;
import com.codigoartesanal.entuliga.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 14/05/15.
 */
@Service
public class MunicipioServiceImpl implements MunicipioService {

    @Autowired
    MunicipioRepository municipioRepository;

    @Override
    public List<Map<String, Object>> listMunicipioByEstado(Long idEstado) {
        Estado estado = new Estado();
        estado.setId(idEstado);
        Iterator<Municipio> itMunicipio = municipioRepository.findAllByEstado(estado).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itMunicipio.hasNext()) {
            Municipio municipio = itMunicipio.next();
            Map<String, Object> dto = convertMunicipioToMap(municipio);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertMunicipioToMap(Municipio municipio) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, municipio.getId());
        map.put(PROPERTY_NOMBRE, municipio.getNombre());
        map.put(PROPERTY_NOMBRE_OFICIAL, municipio.getNombreOficial());
        map.put(PROPERTY_ID_ESTADO, municipio.getEstado().getId());
        return map;
    }
}
