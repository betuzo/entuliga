package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 22/05/15.
 */
public interface ArbitroService {

    String PROPERTY_ID                  = "id";
    String PROPERTY_NOMBRE              = "nombre";
    String PROPERTY_PATERNO             = "paterno";
    String PROPERTY_MATERNO             = "materno";
    String PROPERTY_SEXO                = "sexo";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";
    String PROPERTY_GEO_LOCATION_ID     = "geoLocationId";
    String PROPERTY_CALLE               = "calle";
    String PROPERTY_NO_EXTERIOR         = "noExterior";
    String PROPERTY_NO_INTERIOR         = "noInterior";
    String PROPERTY_CODIGO_POSTAL       = "codigoPostal";
    String PROPERTY_COLONIA_ID          = "coloniaId";
    String PROPERTY_COLONIA_DESC        = "coloniaDesc";
    String PROPERTY_MUNICIPIO_ID        = "municipioId";
    String PROPERTY_MUNICIPIO_DESC      = "municipioDesc";
    String PROPERTY_ESTADO_ID           = "estadoId";
    String PROPERTY_ESTADO_DESC         = "estadoDesc";
    String PROPERTY_PAIS_ID             = "paisId";
    String PROPERTY_PAIS_DESC           = "paisDesc";
    String PROPERTY_LONGITUDE           = "longitude";
    String PROPERTY_LATITUDE            = "latitude";
    String PROPERTY_HAS_FOTO_ARBITRO    = "hasLogoArbitro";
    String PROPERTY_FOTO_ARBITRO        = "logoArbitro";
    String PROPERTY_RUTA_FOTO_ARBITRO   = "rutaLogoArbitro";

    Map<String,Object> createArbitro(Map<String, String> arbitroMap, User user);

    DeleteStatusEnum deleteArbitro(Long idArbitro);

    List<Map<String,Object>> listArbitroByAdmin(User user);

    void updateFotoByArbitro(String foto, Long idArbitro);

    List<Map<String,Object>> listArbitroByTorneoAndContainName(Long idTorneo, String criterio);
}
