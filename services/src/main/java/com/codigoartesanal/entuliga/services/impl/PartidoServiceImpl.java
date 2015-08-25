package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Jornada;
import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 10/06/15.
 */
@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    PartidoRepository partidoRepository;

    @Override
    public List<Map<String, Object>> listPartidoByJornada(Long idJornada) {
        Jornada jornada = new Jornada();
        jornada.setId(idJornada);
        Iterator<Partido> itPartido = partidoRepository.findAllByJornada(jornada).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itPartido.hasNext()) {
            Partido partido = itPartido.next();
            Map<String, Object> dto = convertPartidoToMap(partido);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertPartidoToMap(Partido partido) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, partido.getId());
        map.put(PROPERTY_JORNADA_ID, partido.getJornada().getId());
        map.put(PROPERTY_JORNADA_NOMBRE, partido.getJornada().getNombre());
        map.put(PROPERTY_LOCAL_ID, partido.getLocal().getEquipo().getId());
        map.put(PROPERTY_LOCAL_NOMBRE, partido.getLocal().getEquipo().getNombre());
        map.put(PROPERTY_LOCAL_PUNTOS, partido.getPuntosLocal());
        map.put(PROPERTY_VISITANTE_ID, partido.getVisitante().getEquipo().getId());
        map.put(PROPERTY_VISITANTE_NOMBRE, partido.getVisitante().getEquipo().getNombre());
        map.put(PROPERTY_VISITANTE_PUNTOS, partido.getPuntosVisitante());
        map.put(PROPERTY_CANCHA_ID, partido.getCancha().getId());
        map.put(PROPERTY_CANCHA_NOMBRE, partido.getCancha().getNombre());
        map.put(PROPERTY_HORARIO, partido.getHorario());
        map.put(PROPERTY_STATUS_PARTIDO, partido.getStatus());
        return map;
    }
}
