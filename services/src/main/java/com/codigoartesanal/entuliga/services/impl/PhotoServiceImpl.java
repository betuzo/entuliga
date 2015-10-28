package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import com.codigoartesanal.entuliga.services.TipoPhoto;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by betuzo on 27/10/15.
 */
public class PhotoServiceImpl implements PhotoService {

    @Resource
    Environment env;

    @Override
    public String getValidPath(String path, TipoPhoto tipoPhoto) {
        if (path == null || path.isEmpty()) {
            return tipoPhoto == TipoPhoto.EQUIPO ? PathPhoto.EQUIPO_DEFAULT.getPath() : PathPhoto.JUGADOR_DEFAULT.getPath();
        }

        File file = new File(path);
        if (file == null || !file.exists()) {

        }
        return null;
    }
}
