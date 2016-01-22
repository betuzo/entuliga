package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.services.OriginPhoto;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by betuzo on 16/01/16.
 */
@Service
@Profile("googlestorage")
public class PathWebGoogleCloudImpl implements PathWebService {

    static final String PROPERTY_STATIC_GOOGLE_BUCKET_NAME = "entuliga.service.google.pathWeb";

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path, OriginPhoto originPhoto) {
        String pathBase = PathPhoto.JUGADOR_BASE.getPath();
        if (originPhoto == OriginPhoto.ARBITRO) {
            pathBase = PathPhoto.ARBITRO_BASE.getPath();
        }
        String pathFull = env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME)
                + PathPhoto.PHOTO_BASE.getPath() + pathBase + path;
        return pathFull;
    }

    @Override
    public String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica) {
        String pathFull = env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME)
                + PathPhoto.PHOTO_BASE.getPath() + PathPhoto.EQUIPO_BASE.getPath() + path;
        return pathFull;
    }
}
