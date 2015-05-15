package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 14/05/15.
 */
public interface EstadoService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_NOMBRE          = "nombre";
    public static final String PROPERTY_ABREVIATURA     = "abreviatura";
    public static final String PROPERTY_ID_PAIS         = "idPais";

    List<Map<String, Object>> listEstadoByPais(Long idPais);
}
