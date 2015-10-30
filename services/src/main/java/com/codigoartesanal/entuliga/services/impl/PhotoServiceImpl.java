package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by betuzo on 27/10/15.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Resource
    Environment env;

    @Override
    public String getValidPathFoto(String path) {
        if (path == null || path.isEmpty()) {
            return PathPhoto.JUGADOR_DEFAULT.getPath();
        }

        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + PathPhoto.JUGADOR_BASE.getPath() + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return PathPhoto.JUGADOR_DEFAULT.getPath();
        }
        return PathPhoto.JUGADOR_BASE.getPath() + path;
    }

    @Override
    public String getValidPathLogo(String path, OrigenEstadistica origenEstadistica) {
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
        return PathPhoto.EQUIPO_BASE.getPath() + path;
    }
}
