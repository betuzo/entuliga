package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 11/05/15.
 */
public interface LigaService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_NOMBRE          = "nombre";
    public static final String PROPERTY_NOMBRE_COMPLETO = "nombreCompleto";
    public static final String PROPERTY_TELEFONO        = "telefono";
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

    Map<String,Object> createLiga(Map<String, String> ligaMap, User admin);

    List<Map<String,Object>> listLigaByUser(User user);

    DeleteStatusEnum deleteLiga(Long idLiga);

}
