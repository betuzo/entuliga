package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 1/06/15.
 */
public interface TorneoEquipoService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_TORNEO_ID       = "torneoId";
    public static final String PROPERTY_TORNEO_NOMBRE   = "torneoNombre";
    public static final String PROPERTY_EQUIPO_ID       = "equipoId";
    public static final String PROPERTY_EQUIPO_NOMBRE   = "equipoNombre";
    public static final String PROPERTY_STATUS_EQUIPO   = "statusEquipo";

    public static final String PROPERTY_RESULT          = "result";
    public static final String PROPERTY_MESSAGE         = "message";

    Map<String,Object> createTorneoEquipo(Map<String, String> equipo);

    List<Map<String,Object>> listTorneoEquipoByTorneo(Long idTorneo);

    void deleteTorneoEquipo(Long idTorneoEquipo);
}
