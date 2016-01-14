package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 21/05/15.
 */
public interface EquipoService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_NOMBRE              = "nombre";
    public static final String PROPERTY_ALIAS_EQUIPO        = "aliasEquipo";
    public static final String PROPERTY_HAS_LOGO_EQUIPO     = "hasLogoEquipo";
    public static final String PROPERTY_LOGO_EQUIPO         = "logoEquipo";
    public static final String PROPERTY_RUTA_LOGO_EQUIPO    = "rutaLogoEquipo";

    Map<String,Object> createEquipo(Map<String, String> equipo, User user);

    List<Map<String, Object>> listEquipoByAdmin(User user);

    List<Map<String,Object>> listEquipoByTorneoAndContainName(Long idTorneo, String likeName);

    void updateLogoByEquipo(String logo, Long idEquipo);
}
