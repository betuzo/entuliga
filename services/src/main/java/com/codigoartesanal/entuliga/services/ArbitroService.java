package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 22/05/15.
 */
public interface ArbitroService {

    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_NOMBRE              = "nombre";
    public static final String PROPERTY_PATERNO             = "paterno";
    public static final String PROPERTY_MATERNO             = "materno";
    public static final String PROPERTY_SEXO                = "sexo";
    public static final String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";
    public static final String PROPERTY_GEO_LOCATION_ID     = "geoLocationId";
    public static final String PROPERTY_CALLE               = "calle";
    public static final String PROPERTY_NO_EXTERIOR         = "noExterior";
    public static final String PROPERTY_NO_INTERIOR         = "noInterior";
    public static final String PROPERTY_CODIGO_POSTAL       = "codigoPostal";
    public static final String PROPERTY_COLONIA_ID          = "coloniaId";
    public static final String PROPERTY_COLONIA_DESC        = "coloniaDesc";
    public static final String PROPERTY_MUNICIPIO_ID        = "municipioId";
    public static final String PROPERTY_MUNICIPIO_DESC      = "municipioDesc";
    public static final String PROPERTY_ESTADO_ID           = "estadoId";
    public static final String PROPERTY_ESTADO_DESC         = "estadoDesc";
    public static final String PROPERTY_PAIS_ID             = "paisId";
    public static final String PROPERTY_PAIS_DESC           = "paisDesc";
    public static final String PROPERTY_LONGITUDE           = "longitude";
    public static final String PROPERTY_LATITUDE            = "latitude";
    public static final String PROPERTY_HAS_FOTO_ARBITRO    = "hasLogoArbitro";
    public static final String PROPERTY_FOTO_ARBITRO        = "logoArbitro";
    public static final String PROPERTY_RUTA_FOTO_ARBITRO   = "rutaLogoArbitro";

    Map<String,Object> createArbitro(Map<String, String> arbitroMap, User user);

    DeleteStatusEnum deleteArbitro(Long idArbitro);

    List<Map<String,Object>> listArbitroByAdmin(User user);

}
