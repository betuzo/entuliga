package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import com.codigoartesanal.entuliga.services.TipoPhoto;
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
    public String getValidPath(String path, TipoPhoto tipoPhoto) {
        String pathBase = tipoPhoto == TipoPhoto.EQUIPO ? PathPhoto.EQUIPO_BASE.getPath() : PathPhoto.JUGADOR_BASE.getPath();
        String pathDefault = tipoPhoto == TipoPhoto.EQUIPO ? PathPhoto.EQUIPO_DEFAULT.getPath() : PathPhoto.JUGADOR_DEFAULT.getPath();
        if (path == null || path.isEmpty()) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + pathBase + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return pathDefault;
        }
        return pathBase + path;
    }
}
