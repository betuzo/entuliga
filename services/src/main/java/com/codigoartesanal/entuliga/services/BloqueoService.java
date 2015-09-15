package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
public interface BloqueoService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_PARTIDO_ID          = "partidoId";
    public static final String PROPERTY_TIEMPO_DES          = "tiempoDes";
    public static final String PROPERTY_MINUTO              = "minuto";
    public static final String PROPERTY_SEGUNDO             = "segundo";
    public static final String PROPERTY_ORIGEN              = "origen";
    public static final String PROPERTY_BLOQUEA_ID          = "bloqueaId";
    public static final String PROPERTY_BLOQUEA_NOMBRE      = "bloqueaNombre";
    public static final String PROPERTY_BLOQUEADO_ID        = "bloqueadoId";
    public static final String PROPERTY_BLOQUEADO_NOMBRE    = "bloqueadoNombre";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    Map<String,Object> createBloqueo(Map<String, String> bloqueo);

    void deleteBloqueo(Long idBloqueo);

    List<Map<String,Object>> bloqueosByPartido(Long idPartido);
}
