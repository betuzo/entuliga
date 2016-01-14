package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by betuzo on 27/10/15.
 */
public interface PhotoService {

    static final String PROPERTY_STATIC_FILE_PHOTO = "entuliga.web.pathPhoto";

    String getValidPathWebFoto(String path);

    String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica);

    String getValidNameLogo(String path, Long idEquipo);

    String getValidPathAbsoluteLogo();

    String getValidPathAbsoluteFoto();

    boolean writeFile(byte[] file, String path) throws IOException;

    void deleteLogo(String logo);

    void deleteFoto(String foto);
}
