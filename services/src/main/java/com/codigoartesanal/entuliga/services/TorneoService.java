package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 18/05/15.
 */
public interface TorneoService {
    String PROPERTY_ID              = "id";
    String PROPERTY_LIGA_ID         = "ligaId";
    String PROPERTY_NOMBRE          = "nombre";
    String PROPERTY_CLAVE           = "clave";
    String PROPERTY_DESCRIPCION     = "descripcion";
    String PROPERTY_FECHA_INICIO    = "fechaInicio";
    String PROPERTY_FECHA_FIN       = "fechaFin";
    String PROPERTY_STATUS          = "status";

    Map<String,Object> createTorneo(Map<String, String> torneo, User user);
    DeleteStatusEnum deleteTorneo(Long idTorneo);
    List<Map<String,Object>> listTorneoByLiga(Long idLiga);
    List<Map<String,Object>> listTorneo();
    Map<String,Object> listTorneoByClave(String clave);
}
