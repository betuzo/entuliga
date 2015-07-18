package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Estado;
import com.codigoartesanal.entuliga.model.Pais;
import com.codigoartesanal.entuliga.repositories.EstadoRepository;
import com.codigoartesanal.entuliga.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 14/05/15.
 */
@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public List<Map<String, Object>> listEstadoByPais(Long idPais) {
        Pais pais = new Pais();
        pais.setId(idPais);
        Iterator<Estado> itEstado = estadoRepository.findAllByPais(pais).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itEstado.hasNext()) {
            Estado estado = itEstado.next();
            Map<String, Object> dto = convertEstadoToMap(estado);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertEstadoToMap(Estado estado) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, estado.getId());
        map.put(PROPERTY_NOMBRE, estado.getNombre());
        map.put(PROPERTY_ABREVIATURA, estado.getAbreviatura());
        map.put(PROPERTY_ID_PAIS, estado.getPais().getId());
        return map;
    }
}
