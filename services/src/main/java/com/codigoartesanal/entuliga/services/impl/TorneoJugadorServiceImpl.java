package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.JugadorRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.TorneoJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 4/06/15.
 */
@Service
public class TorneoJugadorServiceImpl implements TorneoJugadorService {

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Autowired
    JugadorRepository jugadorRepository;

    @Override
    public List<Map<String, Object>> listTorneoJugadorByTorneoEquipo(Long idTorneoEquipo) {
        TorneoEquipo torneoEquipo = new TorneoEquipo();
        torneoEquipo.setId(idTorneoEquipo);
        Iterator<TorneoJugador> itTorneoJugador = torneoJugadorRepository.findAllByTorneoEquipo(torneoEquipo).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneoJugador.hasNext()) {
            TorneoJugador torneoJugador = itTorneoJugador.next();
            Map<String, Object> dto = convertTorneoJugadorToMap(torneoJugador);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public Map<String, Object> createTorneoJugador(Map<String, String> jugador) {
        TorneoJugador torneoJugador = convertMapToTorneoJugador(jugador);
        torneoJugador = torneoJugadorRepository.save(torneoJugador);
        torneoJugador.setJugador(jugadorRepository.findOne(torneoJugador.getJugador().getId()));
        return convertTorneoJugadorToMap(torneoJugador);
    }

    @Override
    public DeleteStatusEnum deleteTorneoJugador(Long idTorneoJugador) {
        try {
            torneoJugadorRepository.delete(idTorneoJugador);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    private TorneoJugador convertMapToTorneoJugador(Map<String, String> torneoJugadorMap) {
        TorneoJugador torneoJugador = new TorneoJugador();
        Jugador jugador = new Jugador();
        jugador.setId(Long.valueOf(torneoJugadorMap.get(PROPERTY_JUGADOR_ID)));

        torneoJugador.setJugador(jugador);

        TorneoEquipo torneoEquipo = new TorneoEquipo();
        torneoEquipo.setId(Long.valueOf(torneoJugadorMap.get(PROPERTY_TORNEO_EQUIPO_ID)));

        torneoJugador.setTorneoEquipo(torneoEquipo);
        torneoJugador.setStatusJugador(StatusJugador.valueOf(torneoJugadorMap.get(PROPERTY_STATUS_JUGADOR)));
        torneoJugador.setPosicionJugador(PosicionJugador.valueOf(torneoJugadorMap.get(PROPERTY_POSICION_JUGADOR)));
        torneoJugador.setNumero(torneoJugadorMap.get(PROPERTY_NUMERO_JUGADOR));

        return torneoJugador;
    }

    private Map<String, Object> convertTorneoJugadorToMap(TorneoJugador torneoJugador) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, torneoJugador.getId());
        map.put(PROPERTY_TORNEO_EQUIPO_ID, torneoJugador.getTorneoEquipo().getId());
        map.put(PROPERTY_JUGADOR_ID, torneoJugador.getJugador().getId());
        map.put(PROPERTY_JUGADOR_NOMBRE, torneoJugador.getJugador().getNombreCompleto());
        map.put(PROPERTY_STATUS_JUGADOR, torneoJugador.getStatusJugador().getDescription());
        map.put(PROPERTY_POSICION_JUGADOR, torneoJugador.getPosicionJugador().getDescription());
        map.put(PROPERTY_NUMERO_JUGADOR, torneoJugador.getNumero());

        return map;
    }
}
