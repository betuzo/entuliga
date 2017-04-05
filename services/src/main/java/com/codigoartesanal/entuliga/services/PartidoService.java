package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/06/15.
 */
public interface PartidoService {
    String PROPERTY_ID                  = "id";
    String PROPERTY_TORNEO_ID           = "torneoId";
    String PROPERTY_JORNADA_ID          = "jornadaId";
    String PROPERTY_JORNADA_NOMBRE      = "jornadaNombre";
    String PROPERTY_LOCAL_ID            = "localId";
    String PROPERTY_LOCAL_NOMBRE        = "localNombre";
    String PROPERTY_LOCAL_ALIAS         = "localAlias";
    String PROPERTY_LOCAL_LOGO          = "localLogo";
    String PROPERTY_LOCAL_PUNTOS        = "localPuntos";
    String PROPERTY_LOCAL_COLOR         = "localColor";
    String PROPERTY_VISITANTE_ID        = "visitaId";
    String PROPERTY_VISITANTE_NOMBRE    = "visitaNombre";
    String PROPERTY_VISITANTE_ALIAS     = "visitaAlias";
    String PROPERTY_VISITANTE_LOGO      = "visitaLogo";
    String PROPERTY_VISITANTE_PUNTOS    = "visitaPuntos";
    String PROPERTY_VISITANTE_COLOR     = "visitaColor";
    String PROPERTY_CANCHA_ID           = "canchaId";
    String PROPERTY_CANCHA_NOMBRE       = "canchaNombre";
    String PROPERTY_CANCHA_DOMICILIO    = "canchaDomicilio";
    String PROPERTY_HORARIO             = "horario";
    String PROPERTY_STATUS_PARTIDO      = "statusPartido";

    List<Map<String,Object>> listPartidoByJornada(Long idJornada);

    Map<String,Object> createPartido(Map<String, String> partido);

    DeleteStatusEnum deletePartido(Long idPartido);

    Map<String,Object> partidoById(Long idPartido);
}
