package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.EncesteRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.EncesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 4/09/15.
 */
@Service
public class EncesteServiceImpl implements EncesteService {

    @Autowired
    EncesteRepository encesteRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createEnceste(Map<String, String> encesteMap) {
        Enceste enceste = convertMapToEnceste(encesteMap);
        enceste = encesteRepository.save(enceste);
        acumulaPunto(enceste);
        enceste = populateEnceste(enceste);
        return convertEncesteToMap(enceste);
    }

    @Override
    public List<Map<String, Object>> puntosByPartido(Long idPartido) {
        Partido partido = new Partido();
        partido.setId(idPartido);
        Iterator<Enceste> itEnceste = encesteRepository.findAllByPartido(partido).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itEnceste.hasNext()) {
            Enceste enceste = itEnceste.next();
            Map<String, Object> dto = convertEncesteToMap(enceste);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public void deleteEnceste(Long idEnceste) {
        Enceste enceste = encesteRepository.findById(idEnceste).get();
        encesteRepository.deleteById(idEnceste);
        reducePunto(enceste);
    }

    private Enceste populateEnceste(Enceste enceste){
        enceste.setPartido(partidoRepository.findById(enceste.getPartido().getId()).get());
        enceste.setTirador(torneoJugadorRepository.findById(enceste.getTirador().getId()).get());

        return enceste;
    }

    private void acumulaPunto(Enceste enceste){
        if (enceste.getOrigen() == OrigenEstadistica.LOCAL) {
            partidoRepository.acumulaPuntosLocal(Long.valueOf(enceste.getTipo().getValor()), enceste.getPartido().getId());
        } else if (enceste.getOrigen() == OrigenEstadistica.VISITA) {
            partidoRepository.acumulaPuntosVisita(Long.valueOf(enceste.getTipo().getValor()), enceste.getPartido().getId());
        }
    }

    private void reducePunto(Enceste enceste){
        if (enceste.getOrigen() == OrigenEstadistica.LOCAL) {
            partidoRepository.reducePuntosLocal(Long.valueOf(enceste.getTipo().getValor()), enceste.getPartido().getId());
        } else if (enceste.getOrigen() == OrigenEstadistica.VISITA) {
            partidoRepository.reducePuntosVisita(Long.valueOf(enceste.getTipo().getValor()), enceste.getPartido().getId());
        }
    }

    private Enceste convertMapToEnceste(Map<String, String> encesteMap) {
        Enceste enceste = new Enceste();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(encesteMap.get(PROPERTY_PARTIDO_ID)));
        enceste.setPartido(partido);

        TorneoJugador tirador = new TorneoJugador();
        tirador.setId(Long.valueOf(encesteMap.get(PROPERTY_JUGADOR_ID)));
        enceste.setTirador(tirador);

        enceste.setMinuto(Integer.valueOf(encesteMap.get(PROPERTY_MINUTO)));
        enceste.setSegundo(Integer.valueOf(encesteMap.get(PROPERTY_SEGUNDO)));
        enceste.setTipo(TipoEnceste.valueOf(encesteMap.get(PROPERTY_TIPO)));
        enceste.setValor(enceste.getTipo().getValor());
        enceste.setOrigen(OrigenEstadistica.valueOf(encesteMap.get(PROPERTY_ORIGEN)));
        enceste.setSuccess(Boolean.valueOf(encesteMap.get(PROPERTY_SUCCESS)));
        return enceste;
    }

    private Map<String, Object> convertEncesteToMap(Enceste enceste) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, enceste.getId());
        map.put(PROPERTY_PARTIDO_ID, enceste.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, enceste.getTiempoDescripcion());
        map.put(PROPERTY_MINUTO, enceste.getMinuto());
        map.put(PROPERTY_SEGUNDO, enceste.getSegundo());
        map.put(PROPERTY_TIPO, enceste.getTipo());
        map.put(PROPERTY_TIPO_VALOR, enceste.getTipo().getValor());
        map.put(PROPERTY_ORIGEN, enceste.getOrigen());
        map.put(PROPERTY_JUGADOR_ID, enceste.getTirador().getId());
        map.put(PROPERTY_JUGADOR_NOMBRE, enceste.getTirador().getJugador().getNombreCompleto());
        map.put(PROPERTY_SUCCESS, enceste.isSuccess());
        return map;
    }
}
