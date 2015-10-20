package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.StatusTorneo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.TorneoRepository;
import com.codigoartesanal.entuliga.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 18/05/15.
 */
@Service
public class TorneoServiceImpl implements TorneoService {

    @Autowired
    TorneoRepository torneoRepository;

    @Override
    public Map<String, Object> createTorneo(Map<String, String> torneoMap, User user) {
        Torneo torneo = convertMapToToneo(torneoMap);
        torneo.setClave(generateClaveByNombre(torneo.getNombre()));
        return convertTorneoToMap(torneoRepository.save(torneo));
    }

    @Override
    public List<Map<String, Object>> listTorneoByLiga(Long idLiga) {
        Liga liga = new Liga();
        liga.setId(idLiga);
        Iterator<Torneo> itTorneo = torneoRepository.findAllByLiga(liga).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneo.hasNext()) {
            Torneo torneo = itTorneo.next();
            Map<String, Object> dto = convertTorneoToMap(torneo);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public List<Map<String, Object>> listTorneo() {
        Iterator<Torneo> itTorneo = torneoRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTorneo.hasNext()) {
            Torneo torneo = itTorneo.next();
            Map<String, Object> dto = convertTorneoToMap(torneo);
            copy.add(dto);
        }
        return copy;
    }

    private Torneo convertMapToToneo(Map<String, String> torneoMap) {
        Torneo torneo = new Torneo();
        Liga liga = new Liga();
        if (torneoMap.containsKey(PROPERTY_ID)) {
            torneo = this.get(Long.valueOf(torneoMap.get(PROPERTY_ID)));
        }
        liga.setId(Long.valueOf(torneoMap.get(PROPERTY_LIGA_ID)));
        torneo.setLiga(liga);
        torneo.setNombre(torneoMap.get(PROPERTY_NOMBRE));
        torneo.setDescripcion(torneoMap.get(PROPERTY_DESCRIPCION));
        torneo.setFechaInicio(new Date(Long.valueOf(torneoMap.get(PROPERTY_FECHA_INICIO))));
        torneo.setFechaFin(new Date(Long.valueOf(torneoMap.get(PROPERTY_FECHA_FIN))));
        torneo.setStatus(StatusTorneo.valueOf(torneoMap.get(PROPERTY_STATUS)));
        return torneo;
    }

    private Map<String, Object> convertTorneoToMap(Torneo torneo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, torneo.getId());
        map.put(PROPERTY_LIGA_ID, torneo.getLiga().getId());
        map.put(PROPERTY_NOMBRE, torneo.getNombre());
        map.put(PROPERTY_CLAVE, torneo.getClave());
        map.put(PROPERTY_DESCRIPCION, torneo.getDescripcion());
        map.put(PROPERTY_FECHA_INICIO, torneo.getFechaInicio());
        map.put(PROPERTY_FECHA_FIN, torneo.getFechaFin());
        map.put(PROPERTY_STATUS, torneo.getStatus());
        return map;
    }

    private String generateClaveByNombre(String nombre){
        String clave;
        clave = nombre.replace(" ", "-");
        clave = clave.toLowerCase();
        return clave;
    }

    private Torneo get(Long idTorneo){
        return this.torneoRepository.findOne(idTorneo);
    }
}
