package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Rebote;
import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.TipoRebote;
import com.codigoartesanal.entuliga.model.TorneoJugador;
import com.codigoartesanal.entuliga.repositories.ReboteRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.ReboteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        reboteRepository.delete(idRebote);
    }

    @Override
    public List<Map<String, Object>> rebotesByPartido(Long idPartido) {
        return null;
    }

    private Rebote populateRebote(Rebote rebote){
        rebote.setPartido(partidoRepository.findOne(rebote.getPartido().getId()));
        rebote.setJugador(torneoJugadorRepository.findOne(rebote.getJugador().getId()));

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
        map.put(PROPERTY_JUGADOR_ID, rebote.getJugador().getId());
        map.put(PROPERTY_JUGADOR_NOMBRE, rebote.getJugador().getJugador().getNombreCompleto());
        return map;
    }
    
}
