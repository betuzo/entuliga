package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by betuzo on 27/10/15.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

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
        return PathPhoto.JUGADOR_BASE.getPath() + path;
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
        return PathPhoto.EQUIPO_BASE.getPath() + path;
    }

    @Override
    public String getValidNameLogo(String path, Long idEquipo) {
        String extension=path.substring(path.lastIndexOf("."));
        return idEquipo + extension;
    }

    @Override
    public String getValidPathAbsoluteLogo() {
        // Creating the directory to store file
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO)
                + PathPhoto.EQUIPO_BASE.getPath();
        File dir = new File(pathFull);
        if (!dir.exists())
            dir.mkdirs();

        return dir.getAbsolutePath()
                + File.separator;
    }

    @Override
    public boolean writeFile(byte[] file, String path) throws IOException {
        if (file == null || path == null || path.isEmpty()) {
            return false;
        }

        File serverFile = new File(path);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(file);
        stream.close();

        return true;
    }

    @Override
    public void deleteLogo(String logo) {
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO)
                + PathPhoto.EQUIPO_BASE.getPath() + logo;
        File dir = new File(pathFull);
        if (dir.exists())
            dir.delete();
    }

    @Override
    public void deleteFoto(String foto) {
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO)
                + PathPhoto.JUGADOR_BASE.getPath() + foto;
        File dir = new File(pathFull);
        if (dir.exists())
            dir.delete();
    }
}
