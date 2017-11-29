package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.JornadaRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoCanchaRepository;
import com.codigoartesanal.entuliga.repositories.TorneoEquipoRepository;
import com.codigoartesanal.entuliga.services.PartidoService;
import com.codigoartesanal.entuliga.services.PathWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 10/06/15.
 */
@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoEquipoRepository torneoEquipoRepository;

    @Autowired
    TorneoCanchaRepository torneoCanchaRepository;

    @Autowired
    JornadaRepository jornadaRepository;

    @Autowired
    PathWebService pathWebService;

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

    @Override
    public Map<String, Object> createPartido(Map<String, String> mapPartido) {
        Partido partido = convertMapToPartido(mapPartido);
        partido = populatePartido(partidoRepository.save(partido));
        return convertPartidoToMap(partido);
    }

    @Override
    public DeleteStatusEnum deletePartido(Long idPartido) {
        try {
            partidoRepository.deleteById(idPartido);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    @Override
    public Map<String, Object> partidoById(Long idPartido) {
        Optional<Partido> opPartido = partidoRepository.findById(idPartido);
        if (opPartido.isPresent())
            return convertPartidoToMap(opPartido.get());
        return new HashMap<>();
    }

    private Partido populatePartido(Partido partido) {
        partido.setLocal(torneoEquipoRepository.findById(partido.getLocal().getId()).get());
        partido.setColorLocal(partido.getLocal().getEquipo().getMainColor());
        partido.setVisitante(torneoEquipoRepository.findById(partido.getVisitante().getId()).get());
        partido.setColorVisitante(partido.getVisitante().getEquipo().getMainColor());
        partido.setCancha(torneoCanchaRepository.findById(partido.getCancha().getId()).get());
        partido.setJornada(jornadaRepository.findById(partido.getJornada().getId()).get());
        return partido;
    }

    private Partido convertMapToPartido(Map<String, String> mapPartido) {
        Partido partido = new Partido();
        if (mapPartido.containsKey(PROPERTY_ID)) {
            partido = this.get(Long.valueOf(mapPartido.get(PROPERTY_ID)));
        }

        Jornada jornada = new Jornada();
        jornada.setId(Long.valueOf(mapPartido.get(PROPERTY_JORNADA_ID)));
        partido.setJornada(jornada);

        TorneoEquipo local = new TorneoEquipo();
        local.setId(Long.valueOf(mapPartido.get(PROPERTY_LOCAL_ID)));
        partido.setLocal(local);
        partido.setColorLocal(mapPartido.get(PROPERTY_LOCAL_COLOR));
        partido.setPuntosLocal(Long.valueOf(mapPartido.get(PROPERTY_LOCAL_PUNTOS)));

        TorneoEquipo visita = new TorneoEquipo();
        visita.setId(Long.valueOf(mapPartido.get(PROPERTY_VISITANTE_ID)));
        partido.setVisitante(visita);
        partido.setColorVisitante(mapPartido.get(PROPERTY_VISITANTE_COLOR));
        partido.setPuntosVisitante(Long.valueOf(mapPartido.get(PROPERTY_VISITANTE_PUNTOS)));

        TorneoCancha cancha = new TorneoCancha();
        cancha.setId(Long.valueOf(mapPartido.get(PROPERTY_CANCHA_ID)));
        partido.setCancha(cancha);

        partido.setHorario(new Date(Long.valueOf(mapPartido.get(PROPERTY_HORARIO))));
        partido.setStatus(StatusPartido.valueOf(mapPartido.get(PROPERTY_STATUS_PARTIDO)));

        return partido;
    }

    private Map<String, Object> convertPartidoToMap(Partido partido) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, partido.getId());
        map.put(PROPERTY_TORNEO_ID, partido.getJornada().getTorneo().getId());
        map.put(PROPERTY_JORNADA_ID, partido.getJornada().getId());
        map.put(PROPERTY_JORNADA_NOMBRE, partido.getJornada().getNombre());
        map.put(PROPERTY_LOCAL_ID, partido.getLocal().getId());
        map.put(PROPERTY_LOCAL_NOMBRE, partido.getLocal().getEquipo().getNombre());
        map.put(PROPERTY_LOCAL_ALIAS, partido.getLocal().getEquipo().getAliasEquipo());
        String logoLocal =
                pathWebService.getValidPathWebLogo(partido.getLocal().getEquipo().getRutaLogoEquipo(), OrigenEstadistica.LOCAL);
        map.put(PROPERTY_LOCAL_LOGO, logoLocal);
        map.put(PROPERTY_LOCAL_PUNTOS, partido.getPuntosLocal());
        map.put(PROPERTY_LOCAL_COLOR, partido.getColorLocal());
        map.put(PROPERTY_VISITANTE_ID, partido.getVisitante().getId());
        map.put(PROPERTY_VISITANTE_NOMBRE, partido.getVisitante().getEquipo().getNombre());
        map.put(PROPERTY_VISITANTE_ALIAS, partido.getVisitante().getEquipo().getAliasEquipo());
        String logoVisita =
                pathWebService.getValidPathWebLogo(partido.getVisitante().getEquipo().getRutaLogoEquipo(), OrigenEstadistica.VISITA);
        map.put(PROPERTY_VISITANTE_LOGO, logoVisita);
        map.put(PROPERTY_VISITANTE_PUNTOS, partido.getPuntosVisitante());
        map.put(PROPERTY_VISITANTE_COLOR, partido.getColorVisitante());
        map.put(PROPERTY_CANCHA_ID, partido.getCancha().getId());
        map.put(PROPERTY_CANCHA_NOMBRE, partido.getCancha().getCancha().getNombre());
        map.put(PROPERTY_CANCHA_DOMICILIO, partido.getCancha().getCancha().getDomicilio());
        map.put(PROPERTY_HORARIO, partido.getHorario().getTime());
        map.put(PROPERTY_STATUS_PARTIDO, partido.getStatus());
        return map;
    }
    private Partido get(Long idPartido){
        return this.partidoRepository.findById(idPartido).get();
    }

}
