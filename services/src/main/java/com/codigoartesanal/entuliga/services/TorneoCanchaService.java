package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 25/08/15.
 */
public interface TorneoCanchaService {
    String PROPERTY_ID                  = "id";
    String PROPERTY_TORNEO_ID           = "torneoId";
    String PROPERTY_TORNEO_NOMBRE       = "torneoNombre";
    String PROPERTY_CANCHA_ID           = "canchaId";
    String PROPERTY_CANCHA_NOMBRE       = "canchaNombre";
    String PROPERTY_STATUS_CANCHA       = "statusCancha";

    Map<String,Object> createTorneoCancha(Map<String, String> torneoJornada);
    DeleteStatusEnum deleteTorneoCancha(Long idTorneoJornada);
    List<Map<String,Object>> listCanchaByTorneo(Long idTorneo);
}
