package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PathWeb;
import com.codigoartesanal.entuliga.services.PhotoService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by betuzo on 16/01/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class PathWebLocalImpl implements PathWeb {

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path) {
        if (path == null || path.isEmpty()) {
            return PathPhoto.JUGADOR_DEFAULT.getPath();
        }

        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + PathPhoto.JUGADOR_BASE.getPath() + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return PathPhoto.JUGADOR_DEFAULT.getPath();
        }
        return PathPhoto.PHOTO_BASE.getPath() + PathPhoto.JUGADOR_BASE.getPath() + path;
    }

    @Override
    public String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica) {
        String pathDefault = PathPhoto.EQUIPO_DEFAULT.getPath();
        if (origenEstadistica != null) {
            pathDefault = origenEstadistica == OrigenEstadistica.VISITA ?
                    PathPhoto.EQUIPO_DEFAULT_VISITA.getPath() : PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath();
        }
        if (path == null || path.isEmpty()) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + PathPhoto.EQUIPO_BASE.getPath() + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return pathDefault;
        }
        return PathPhoto.PHOTO_BASE.getPath() + PathPhoto.EQUIPO_BASE.getPath() + path;
    }
}
