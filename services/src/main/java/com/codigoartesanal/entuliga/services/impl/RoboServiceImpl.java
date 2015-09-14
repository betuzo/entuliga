package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.Robo;
import com.codigoartesanal.entuliga.model.TorneoJugador;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.RoboRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.RoboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class RoboServiceImpl implements RoboService{

    @Autowired
    RoboRepository roboRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createRobo(Map<String, String> roboMap) {
        Robo robo = convertMapToRobo(roboMap);
        robo = roboRepository.save(robo);
        robo = populateRobo(robo);
        return convertRoboToMap(robo);
    }

    @Override
    public void deleteRobo(Long idRobo) {
        roboRepository.delete(idRobo);
    }

    @Override
    public List<Map<String, Object>> robosByPartido(Long idPartido) {
        return null;
    }

    private Robo populateRobo(Robo robo){
        robo.setPartido(partidoRepository.findOne(robo.getPartido().getId()));
        robo.setRobador(torneoJugadorRepository.findOne(robo.getRobador().getId()));
        robo.setPerdedor(torneoJugadorRepository.findOne(robo.getPerdedor().getId()));

        return robo;
    }

    private Robo convertMapToRobo(Map<String, String> roboMap) {
        Robo robo = new Robo();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(roboMap.get(PROPERTY_PARTIDO_ID)));
        robo.setPartido(partido);

        TorneoJugador robador = new TorneoJugador();
        robador.setId(Long.valueOf(roboMap.get(PROPERTY_ROBADOR_ID)));
        robo.setRobador(robador);

        TorneoJugador perdedor = new TorneoJugador();
        perdedor.setId(Long.valueOf(roboMap.get(PROPERTY_PERDEDOR_ID)));
        robo.setPerdedor(perdedor);

        robo.setMinuto(Integer.valueOf(roboMap.get(PROPERTY_MINUTO)));
        robo.setSegundo(Integer.valueOf(roboMap.get(PROPERTY_SEGUNDO)));
        robo.setOrigen(OrigenEstadistica.valueOf(roboMap.get(PROPERTY_ORIGEN)));
        return robo;
    }

    private Map<String, Object> convertRoboToMap(Robo robo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, robo.getId());
        map.put(PROPERTY_PARTIDO_ID, robo.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, robo.getTiempoDescripcion());
        map.put(PROPERTY_ORIGEN, robo.getOrigen());
        map.put(PROPERTY_MINUTO, robo.getMinuto());
        map.put(PROPERTY_SEGUNDO, robo.getSegundo());
        map.put(PROPERTY_ROBADOR_ID, robo.getRobador().getId());
        map.put(PROPERTY_ROBADOR_NOMBRE, robo.getRobador().getJugador().getNombreCompleto());
        map.put(PROPERTY_PERDEDOR_ID, robo.getPerdedor().getId());
        map.put(PROPERTY_PERDEDOR_NOMBRE, robo.getPerdedor().getJugador().getNombreCompleto());
        return map;
    }
    
}
