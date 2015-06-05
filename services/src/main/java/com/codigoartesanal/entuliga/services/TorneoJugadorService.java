package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 4/06/15.
 */
public interface TorneoJugadorService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_TORNEO_EQUIPO_ID    = "torneoEquipoId";
    public static final String PROPERTY_JUGADOR_ID          = "jugadorId";
    public static final String PROPERTY_JUGADOR_NOMBRE      = "jugadorNombre";
    public static final String PROPERTY_STATUS_JUGADOR      = "statusJugador";
    public static final String PROPERTY_POSICION_JUGADOR    = "posicionJugador";
    public static final String PROPERTY_NUMERO_JUGADOR      = "numeroJugador";

    List<Map<String,Object>> listTorneoJugadorByTorneoEquipo(Long idTorneoEquipo);

    Map<String, Object> createTorneoJugador(Map<String, String> jugador);
}
