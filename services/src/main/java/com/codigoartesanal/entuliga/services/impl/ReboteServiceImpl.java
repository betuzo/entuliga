package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.ReboteRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.ReboteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class ReboteServiceImpl implements ReboteService {

    @Autowired
    ReboteRepository reboteRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createRebote(Map<String, String> reboteMap) {
        Rebote rebote = convertMapToRebote(reboteMap);
        rebote = reboteRepository.save(rebote);
        rebote = populateRebote(rebote);
        return convertReboteToMap(rebote);
    }

    @Override
    public void deleteRebote(Long idRebote) {
        reboteRepository.deleteById(idRebote);
    }

    @Override
    public List<Map<String, Object>> rebotesByPartido(Long idPartido) {
        Partido partido = new Partido();
        partido.setId(idPartido);
        Iterator<Rebote> itRebote = reboteRepository.findAllByPartido(partido).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itRebote.hasNext()) {
            Rebote rebote = itRebote.next();
            Map<String, Object> dto = convertReboteToMap(rebote);
            copy.add(dto);
        }
        return copy;
    }

    private Rebote populateRebote(Rebote rebote){
        rebote.setPartido(partidoRepository.findById(rebote.getPartido().getId()).get());
        rebote.setJugador(torneoJugadorRepository.findById(rebote.getJugador().getId()).get());

        return rebote;
    }

    private Rebote convertMapToRebote(Map<String, String> reboteMap) {
        Rebote rebote = new Rebote();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(reboteMap.get(PROPERTY_PARTIDO_ID)));
        rebote.setPartido(partido);

        TorneoJugador entra = new TorneoJugador();
        entra.setId(Long.valueOf(reboteMap.get(PROPERTY_JUGADOR_ID)));
        rebote.setJugador(entra);

        rebote.setMinuto(Integer.valueOf(reboteMap.get(PROPERTY_MINUTO)));
        rebote.setSegundo(Integer.valueOf(reboteMap.get(PROPERTY_SEGUNDO)));
        rebote.setTipo(TipoRebote.valueOf(reboteMap.get(PROPERTY_TIPO)));
        rebote.setOrigen(OrigenEstadistica.valueOf(reboteMap.get(PROPERTY_ORIGEN)));
        return rebote;
    }

    private Map<String, Object> convertReboteToMap(Rebote rebote) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, rebote.getId());
        map.put(PROPERTY_PARTIDO_ID, rebote.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, rebote.getTiempoDescripcion());
        map.put(PROPERTY_MINUTO, rebote.getMinuto());
        map.put(PROPERTY_SEGUNDO, rebote.getSegundo());
        map.put(PROPERTY_TIPO, rebote.getTipo());
        map.put(PROPERTY_ORIGEN, rebote.getOrigen());
        map.put(PROPERTY_JUGADOR_ID, rebote.getJugador().getId());
        map.put(PROPERTY_JUGADOR_NOMBRE, rebote.getJugador().getJugador().getNombreCompleto());
        return map;
    }
    
}
