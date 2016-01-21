package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 06/06/15.
 */
public interface JornadaService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_TORNEO_ID       = "torneoId";
    public static final String PROPERTY_TORNEO_NOMBRE   = "torneoNombre";
    public static final String PROPERTY_NOMBRE          = "nombre";
    public static final String PROPERTY_FASE            = "fase";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    List<Map<String,Object>> listJornadaByTorneo(Long idTorneo);

    Map<String,Object> createJornada(Map<String, String> jornada);

    DeleteStatusEnum deleteJornada(Long idJornada);
}
