package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 21/05/15.
 */
public interface EquipoService {
    String PROPERTY_ID                  = "id";
    String PROPERTY_NOMBRE              = "nombre";
    String PROPERTY_ALIAS_EQUIPO        = "aliasEquipo";
    String PROPERTY_HAS_LOGO_EQUIPO     = "hasLogoEquipo";
    String PROPERTY_LOGO_EQUIPO         = "logoEquipo";
    String PROPERTY_RUTA_LOGO_EQUIPO    = "rutaLogoEquipo";
    String PROPERTY_MAIN_COLOR          = "mainColor";

    Map<String,Object> createEquipo(Map<String, String> equipo, User user);

    DeleteStatusEnum deleteEquipo(Long idEquipo);

    List<Map<String, Object>> listEquipoByAdmin(User user);

    List<Map<String,Object>> listEquipoByTorneoAndContainName(Long idTorneo, String likeName);

    void updateLogoByEquipo(String logo, Long idEquipo);

}
