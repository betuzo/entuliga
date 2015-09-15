package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.FaltaRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.FaltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class FaltaServiceImpl implements FaltaService {

    @Autowired
    FaltaRepository faltaRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createFalta(Map<String, String> faltaMap) {
        Falta falta = convertMapToFalta(faltaMap);
        falta = faltaRepository.save(falta);
        falta = populateFalta(falta);
        return convertFaltaToMap(falta);
    }

    @Override
    public void deleteFalta(Long idFalta) {
        faltaRepository.delete(idFalta);
    }

    @Override
    public List<Map<String, Object>> faltasByPartido(Long idPartido) {
        Partido partido = new Partido();
        partido.setId(idPartido);
        Iterator<Falta> itFalte = faltaRepository.findAllByPartido(partido).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itFalte.hasNext()) {
            Falta falta = itFalte.next();
            Map<String, Object> dto = convertFaltaToMap(falta);
            copy.add(dto);
        }
        return copy;
    }

    private Falta populateFalta(Falta falta){
        falta.setPartido(partidoRepository.findOne(falta.getPartido().getId()));
        falta.setRecibe(torneoJugadorRepository.findOne(falta.getRecibe().getId()));
        falta.setInfractor(torneoJugadorRepository.findOne(falta.getInfractor().getId()));

        return falta;
    }

    private Falta convertMapToFalta(Map<String, String> faltaMap) {
        Falta falta = new Falta();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(faltaMap.get(PROPERTY_PARTIDO_ID)));
        falta.setPartido(partido);

        TorneoJugador infractor = new TorneoJugador();
        infractor.setId(Long.valueOf(faltaMap.get(PROPERTY_INFRACTOR_ID)));
        falta.setInfractor(infractor);

        TorneoJugador receptor = new TorneoJugador();
        receptor.setId(Long.valueOf(faltaMap.get(PROPERTY_RECEPTOR_ID)));
        falta.setRecibe(receptor);

        falta.setMinuto(Integer.valueOf(faltaMap.get(PROPERTY_MINUTO)));
        falta.setSegundo(Integer.valueOf(faltaMap.get(PROPERTY_SEGUNDO)));
        falta.setTipo(TipoFalta.valueOf(faltaMap.get(PROPERTY_TIPO)));
        falta.setOrigen(OrigenEstadistica.valueOf(faltaMap.get(PROPERTY_ORIGEN)));

        return falta;
    }

    private Map<String, Object> convertFaltaToMap(Falta falta) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, falta.getId());
        map.put(PROPERTY_PARTIDO_ID, falta.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, falta.getTiempoDescripcion());
        map.put(PROPERTY_MINUTO, falta.getMinuto());
        map.put(PROPERTY_SEGUNDO, falta.getSegundo());
        map.put(PROPERTY_TIPO, falta.getTipo());
        map.put(PROPERTY_ORIGEN, falta.getOrigen());
        map.put(PROPERTY_INFRACTOR_ID, falta.getInfractor().getId());
        map.put(PROPERTY_INFRACTOR_NOMBRE, falta.getInfractor().getJugador().getNombreCompleto());
        map.put(PROPERTY_RECEPTOR_ID, falta.getRecibe().getId());
        map.put(PROPERTY_RECEPTOR_NOMBRE, falta.getRecibe().getJugador().getNombreCompleto());
        return map;
    }
}
