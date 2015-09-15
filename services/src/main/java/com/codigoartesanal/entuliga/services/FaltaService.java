package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
public interface FaltaService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_PARTIDO_ID          = "partidoId";
    public static final String PROPERTY_TIEMPO_DES          = "tiempoDes";
    public static final String PROPERTY_MINUTO              = "minuto";
    public static final String PROPERTY_SEGUNDO             = "segundo";
    public static final String PROPERTY_TIPO                = "tipo";
    public static final String PROPERTY_ORIGEN              = "origen";
    public static final String PROPERTY_INFRACTOR_ID        = "infractorId";
    public static final String PROPERTY_INFRACTOR_NOMBRE    = "infractorNombre";
    public static final String PROPERTY_RECEPTOR_ID         = "receptorId";
    public static final String PROPERTY_RECEPTOR_NOMBRE     = "receptorNombre";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    Map<String,Object> createFalta(Map<String, String> falta);

    void deleteFalta(Long idFalta);

    List<Map<String,Object>> faltasByPartido(Long idPartido);
}
