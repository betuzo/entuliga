package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Asistencia;
import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.TorneoJugador;
import com.codigoartesanal.entuliga.repositories.AsistenciaRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    AsistenciaRepository asistenciaRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createAsistencia(Map<String, String> asistenciaMap) {
        Asistencia asistencia = convertMapToAsistencia(asistenciaMap);
        asistencia = asistenciaRepository.save(asistencia);
        asistencia = populateAsistencia(asistencia);
        return convertAsistenciaToMap(asistencia);
    }

    @Override
    public void deleteAsistencia(Long idAsistencia) {
        asistenciaRepository.delete(idAsistencia);
    }

    @Override
    public List<Map<String, Object>> asistenciasByPartido(Long idPartido) {
        return null;
    }

    private Asistencia populateAsistencia(Asistencia asistencia){
        asistencia.setPartido(partidoRepository.findOne(asistencia.getPartido().getId()));
        asistencia.setAsiste(torneoJugadorRepository.findOne(asistencia.getAsiste().getId()));
        asistencia.setAsistido(torneoJugadorRepository.findOne(asistencia.getAsistido().getId()));

        return asistencia;
    }

    private Asistencia convertMapToAsistencia(Map<String, String> asistenciaMap) {
        Asistencia asistencia = new Asistencia();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(asistenciaMap.get(PROPERTY_PARTIDO_ID)));
        asistencia.setPartido(partido);

        TorneoJugador entra = new TorneoJugador();
        entra.setId(Long.valueOf(asistenciaMap.get(PROPERTY_ASISTE_ID)));
        asistencia.setAsiste(entra);

        TorneoJugador sale = new TorneoJugador();
        sale.setId(Long.valueOf(asistenciaMap.get(PROPERTY_ASISTIDO_ID)));
        asistencia.setAsistido(sale);

        asistencia.setMinuto(Integer.valueOf(asistenciaMap.get(PROPERTY_MINUTO)));
        asistencia.setSegundo(Integer.valueOf(asistenciaMap.get(PROPERTY_SEGUNDO)));
        return asistencia;
    }

    private Map<String, Object> convertAsistenciaToMap(Asistencia asistencia) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, asistencia.getId());
        map.put(PROPERTY_PARTIDO_ID, asistencia.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, asistencia.getTiempoDescripcion());
        map.put(PROPERTY_MINUTO, asistencia.getMinuto());
        map.put(PROPERTY_SEGUNDO, asistencia.getSegundo());
        map.put(PROPERTY_ASISTE_ID, asistencia.getAsiste().getId());
        map.put(PROPERTY_ASISTE_NOMBRE, asistencia.getAsiste().getJugador().getNombreCompleto());
        map.put(PROPERTY_ASISTIDO_ID, asistencia.getAsistido().getId());
        map.put(PROPERTY_ASISTIDO_NOMBRE, asistencia.getAsistido().getJugador().getNombreCompleto());
        return map;
    }
}
