package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;

/**
 * Created by betuzo on 27/10/15.
 */
public interface PhotoService {

    static final String PROPERTY_STATIC_FILE_PHOTO = "entuliga.web.pathPhoto";

    String getValidPathFoto(String path);

    String getValidPathLogo(String path, OrigenEstadistica origenEstadistica);
}
