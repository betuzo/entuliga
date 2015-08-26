package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 10/06/15.
 */
public interface PartidoService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_JORNADA_ID          = "jornadaId";
    public static final String PROPERTY_JORNADA_NOMBRE      = "jornadaNombre";
    public static final String PROPERTY_LOCAL_ID            = "localId";
    public static final String PROPERTY_LOCAL_NOMBRE        = "localNombre";
    public static final String PROPERTY_LOCAL_PUNTOS        = "localPuntos";
    public static final String PROPERTY_VISITANTE_ID        = "visitaId";
    public static final String PROPERTY_VISITANTE_NOMBRE    = "visitaNombre";
    public static final String PROPERTY_VISITANTE_PUNTOS    = "visitaPuntos";
    public static final String PROPERTY_CANCHA_ID           = "canchaId";
    public static final String PROPERTY_CANCHA_NOMBRE       = "canchaNombre";
    public static final String PROPERTY_HORARIO             = "horario";
    public static final String PROPERTY_STATUS_PARTIDO      = "statusPartido";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    List<Map<String,Object>> listPartidoByJornada(Long idJornada);

    Map<String,Object> createPartido(Map<String, String> partido);

    void deletePartido(Long idPartido);
}
