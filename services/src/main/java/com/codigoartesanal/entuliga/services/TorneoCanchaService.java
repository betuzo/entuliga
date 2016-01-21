package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 25/08/15.
 */
public interface TorneoCanchaService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_TORNEO_ID           = "torneoId";
    public static final String PROPERTY_TORNEO_NOMBRE       = "torneoNombre";
    public static final String PROPERTY_CANCHA_ID           = "canchaId";
    public static final String PROPERTY_CANCHA_NOMBRE       = "canchaNombre";
    public static final String PROPERTY_STATUS_CANCHA       = "statusCancha";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    Map<String,Object> createTorneoCancha(Map<String, String> torneoJornada);
    DeleteStatusEnum deleteTorneoCancha(Long idTorneoJornada);
    List<Map<String,Object>> listCanchaByTorneo(Long idTorneo);
}
