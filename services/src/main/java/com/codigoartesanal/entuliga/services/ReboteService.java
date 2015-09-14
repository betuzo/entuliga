package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
public interface ReboteService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_PARTIDO_ID          = "partidoId";
    public static final String PROPERTY_TIEMPO_DES          = "tiempoDes";
    public static final String PROPERTY_MINUTO              = "minuto";
    public static final String PROPERTY_SEGUNDO             = "segundo";
    public static final String PROPERTY_TIPO                = "tipo";
    public static final String PROPERTY_ORIGEN              = "origen";
    public static final String PROPERTY_JUGADOR_ID          = "jugadorId";
    public static final String PROPERTY_JUGADOR_NOMBRE      = "jugadorNombre";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    Map<String,Object> createRebote(Map<String, String> rebote);

    void deleteRebote(Long idRebote);

    List<Map<String,Object>> rebotesByPartido(Long idPartido);
}
