package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 14/05/15.
 */
public interface MunicipioService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_NOMBRE          = "nombre";
    public static final String PROPERTY_NOMBRE_OFICIAL  = "nombreOficial";
    public static final String PROPERTY_ID_ESTADO       = "idEstado";

    List<Map<String, Object>> listMunicipioByEstado(Long idEstado);
}
