package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 17/06/16.
 */
public interface TorneoArbitroService {
    String PROPERTY_ID                  = "id";
    String PROPERTY_TORNEO_ID           = "torneoId";
    String PROPERTY_TORNEO_NOMBRE       = "torneoNombre";
    String PROPERTY_ARBITRO_ID           = "arbitroId";
    String PROPERTY_ARBITRO_NOMBRE       = "arbitroNombre";
    String PROPERTY_STATUS_ARBITRO       = "statusArbitro";

    Map<String,Object> createTorneoArbitro(Map<String, String> torneoArbitro);
    DeleteStatusEnum deleteTorneoArbitro(Long idTorneoArbitro);
    List<Map<String,Object>> listArbitroByTorneo(Long idTorneo);
}
