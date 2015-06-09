package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 06/06/15.
 */
public interface JornadaService {
    List<Map<String,Object>> listJornadaByTorneo(Long idTorneo);

    Map<String,Object> createJornada(Map<String, String> jornada);
}
