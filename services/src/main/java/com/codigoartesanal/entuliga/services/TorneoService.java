package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 18/05/15.
 */
public interface TorneoService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_LIGA_ID         = "ligaId";
    public static final String PROPERTY_NOMBRE          = "nombre";
    public static final String PROPERTY_CLAVE           = "clave";
    public static final String PROPERTY_DESCRIPCION     = "descripcion";
    public static final String PROPERTY_FECHA_INICIO    = "fechaInicio";
    public static final String PROPERTY_FECHA_FIN       = "fechaFin";
    public static final String PROPERTY_STATUS          = "status";

    Map<String,Object> createTorneo(Map<String, String> torneo, User user);
    List<Map<String,Object>> listTorneoByLiga(Long idLiga);
    List<Map<String,Object>> listTorneo();
    Map<String,Object> listTorneoByClave(String clave);
}
