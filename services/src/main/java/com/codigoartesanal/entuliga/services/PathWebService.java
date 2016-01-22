package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;

/**
 * Created by betuzo on 16/01/16.
 */
public interface PathWebService {

    static final String PROPERTY_STATIC_FILE_PHOTO = "entuliga.web.pathPhoto";

    String getValidPathWebFoto(String path, OriginPhoto originPhoto);

    String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica);
}
