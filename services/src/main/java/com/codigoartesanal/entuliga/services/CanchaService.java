package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.Cancha;
import com.codigoartesanal.entuliga.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 25/08/15.
 */
public interface CanchaService {

    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_NOMBRE          = "nombre";
    public static final String PROPERTY_ALIAS           = "alias";
    public static final String PROPERTY_DESCRIPCION     = "descripcion";
    public static final String PROPERTY_GEO_LOCATION_ID = "geoLocationId";
    public static final String PROPERTY_CALLE           = "calle";
    public static final String PROPERTY_NO_EXTERIOR     = "noExterior";
    public static final String PROPERTY_NO_INTERIOR     = "noInterior";
    public static final String PROPERTY_CODIGO_POSTAL   = "codigoPostal";
    public static final String PROPERTY_COLONIA_ID      = "coloniaId";
    public static final String PROPERTY_COLONIA_DESC    = "coloniaDesc";
    public static final String PROPERTY_MUNICIPIO_ID    = "municipioId";
    public static final String PROPERTY_MUNICIPIO_DESC  = "municipioDesc";
    public static final String PROPERTY_ESTADO_ID       = "estadoId";
    public static final String PROPERTY_ESTADO_DESC     = "estadoDesc";
    public static final String PROPERTY_PAIS_ID         = "paisId";
    public static final String PROPERTY_PAIS_DESC       = "paisDesc";
    public static final String PROPERTY_LONGITUDE       = "longitude";
    public static final String PROPERTY_LATITUDE        = "latitude";

    Cancha createCancha(Map<String, String> liga, User user);

    List<Map<String,Object>> listCanchaByUser(User user);

    List<Map<String,Object>> listCanchaByTorneoAndContainName(Long idTorneo, String likeName);
}
