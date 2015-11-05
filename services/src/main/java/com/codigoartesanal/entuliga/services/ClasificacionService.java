package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 21/10/15.
 */
public interface ClasificacionService {
    public static final String PROPERTY_EQUIPO_ID               = "equipoId";
    public static final String PROPERTY_TORNEO_ID               = "torneoId";
    public static final String PROPERTY_EQUIPO_NOMBRE           = "equipoNombre";
    public static final String PROPERTY_EQUIPO_LOGO             = "equipoLogo";

    public static final String PROPERTY_PUNTOS_LOCAL            = "puntosLocal";
    public static final String PROPERTY_PUNTOS_LOCAL_CONTRA     = "puntosLocalContra";
    public static final String PROPERTY_GANADOS_LOCAL           = "ganadosLocal";
    public static final String PROPERTY_PERDIDOS_LOCAL          = "perdidosLocal";
    public static final String PROPERTY_JUGADOS_LOCAL           = "jugadosLocal";

    public static final String PROPERTY_PUNTOS_VISITA           = "puntosVisita";
    public static final String PROPERTY_PUNTOS_VISITA_CONTRA    = "puntosVisitaContra";
    public static final String PROPERTY_GANADOS_VISITA          = "ganadosVisita";
    public static final String PROPERTY_PERDIDOS_VISITA         = "perdidosVisita";
    public static final String PROPERTY_JUGADOS_VISITA          = "jugadosVisita";

    public static final String PROPERTY_TOTAL_PUNTOS_FAVOR      = "totalPuntosFavor";
    public static final String PROPERTY_TOTAL_PUNTOS_CONTRA     = "totalPuntosContra";
    public static final String PROPERTY_DIF_PUNTOS              = "diferenciaPuntos";
    public static final String PROPERTY_TOTAL_GANADOS           = "totalGanados";
    public static final String PROPERTY_TOTAL_PERDIDOS          = "totalPerdidos";
    public static final String PROPERTY_TOTAL_JUGADOS           = "totalJugados";

    List<Map<String,Object>> listClasificacionByTorneo(Long idTorneo);
}
