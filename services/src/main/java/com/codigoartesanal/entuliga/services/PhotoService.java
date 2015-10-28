package com.codigoartesanal.entuliga.services;

/**
 * Created by betuzo on 27/10/15.
 */
public interface PhotoService {

    static final String PROPERTY_STATIC_FILE_PHOTO = "entuliga.web.pathPhoto";

    String getValidPath(String path, TipoPhoto tipoPhoto);
}
