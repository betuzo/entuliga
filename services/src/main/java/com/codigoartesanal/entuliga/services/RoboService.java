package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
public interface RoboService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_PARTIDO_ID          = "partidoId";
    public static final String PROPERTY_TIEMPO_DES          = "tiempoDes";
    public static final String PROPERTY_MINUTO              = "minuto";
    public static final String PROPERTY_SEGUNDO             = "segundo";
    public static final String PROPERTY_ORIGEN              = "origen";
    public static final String PROPERTY_ROBADOR_ID          = "robadorId";
    public static final String PROPERTY_ROBADOR_NOMBRE      = "robadorNombre";
    public static final String PROPERTY_PERDEDOR_ID         = "perdedorId";
    public static final String PROPERTY_PERDEDOR_NOMBRE     = "perdedorNombre";

    Map<String,Object> createRobo(Map<String, String> robo);

    void deleteRobo(Long idRobo);

    List<Map<String,Object>> robosByPartido(Long idPartido);
}
