package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Bloqueo;
import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.TorneoJugador;
import com.codigoartesanal.entuliga.repositories.BloqueoRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.BloqueoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class BloqueoServiceImpl implements BloqueoService {
    
    @Autowired
    BloqueoRepository bloqueoRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createBloqueo(Map<String, String> bloqueoMap) {
        Bloqueo bloqueo = convertMapToBloqueo(bloqueoMap);
        bloqueo = bloqueoRepository.save(bloqueo);
        bloqueo = populateBloqueo(bloqueo);
        return convertBloqueoToMap(bloqueo);
    }

    @Override
    public void deleteBloqueo(Long idBloqueo) {
        bloqueoRepository.delete(idBloqueo);
    }

    @Override
    public List<Map<String, Object>> bloqueosByPartido(Long idPartido) {
        return null;
    }

    private Bloqueo populateBloqueo(Bloqueo bloqueo){
        bloqueo.setPartido(partidoRepository.findOne(bloqueo.getPartido().getId()));
        bloqueo.setBloquea(torneoJugadorRepository.findOne(bloqueo.getBloquea().getId()));
        bloqueo.setBloqueado(torneoJugadorRepository.findOne(bloqueo.getBloqueado().getId()));

        return bloqueo;
    }

    private Bloqueo convertMapToBloqueo(Map<String, String> bloqueoMap) {
        Bloqueo bloqueo = new Bloqueo();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(bloqueoMap.get(PROPERTY_PARTIDO_ID)));
        bloqueo.setPartido(partido);

        TorneoJugador bloquea = new TorneoJugador();
        bloquea.setId(Long.valueOf(bloqueoMap.get(PROPERTY_BLOQUEA_ID)));
        bloqueo.setBloquea(bloquea);

        TorneoJugador bloqueado = new TorneoJugador();
        bloqueado.setId(Long.valueOf(bloqueoMap.get(PROPERTY_BLOQUEADO_ID)));
        bloqueo.setBloqueado(bloqueado);

        bloqueo.setMinuto(Integer.valueOf(bloqueoMap.get(PROPERTY_MINUTO)));
        bloqueo.setSegundo(Integer.valueOf(bloqueoMap.get(PROPERTY_SEGUNDO)));
        bloqueo.setOrigen(OrigenEstadistica.valueOf(bloqueoMap.get(PROPERTY_ORIGEN)));

        return bloqueo;
    }

    private Map<String, Object> convertBloqueoToMap(Bloqueo bloqueo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, bloqueo.getId());
        map.put(PROPERTY_PARTIDO_ID, bloqueo.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, bloqueo.getTiempoDescripcion());
        map.put(PROPERTY_MINUTO, bloqueo.getMinuto());
        map.put(PROPERTY_SEGUNDO, bloqueo.getSegundo());
        map.put(PROPERTY_ORIGEN, bloqueo.getOrigen());
        map.put(PROPERTY_BLOQUEA_ID, bloqueo.getBloquea().getId());
        map.put(PROPERTY_BLOQUEA_NOMBRE, bloqueo.getBloquea().getJugador().getNombreCompleto());
        map.put(PROPERTY_BLOQUEADO_ID, bloqueo.getBloqueado().getId());
        map.put(PROPERTY_BLOQUEADO_NOMBRE, bloqueo.getBloqueado().getJugador().getNombreCompleto());
        return map;
    }
}
