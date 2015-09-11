package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
public interface AsistenciaService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_PARTIDO_ID          = "partidoId";
    public static final String PROPERTY_TIEMPO_DES          = "tiempoDes";
    public static final String PROPERTY_MINUTO              = "minuto";
    public static final String PROPERTY_SEGUNDO             = "segundo";
    public static final String PROPERTY_ASISTE_ID           = "asisteId";
    public static final String PROPERTY_ASISTE_NOMBRE       = "asisteNombre";
    public static final String PROPERTY_ASISTIDO_ID         = "asistidoId";
    public static final String PROPERTY_ASISTIDO_NOMBRE     = "asistidoNombre";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    Map<String,Object> createAsistencia(Map<String, String> asistencia);

    void deleteAsistencia(Long idAsistencia);

    List<Map<String,Object>> asistenciasByPartido(Long idPartido);
}
