package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import com.codigoartesanal.entuliga.services.StorageImage;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    StorageImage storageImage;

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

    @Override
    public String getValidNameLogo(String path, Long idEquipo) {
        String extension=path.substring(path.lastIndexOf("."));
        return idEquipo + extension;
    }

    @Override
    public String getValidPathAbsoluteLogo() {
        // Creating the directory to store file
        return getValidPathAbsolute(PathPhoto.EQUIPO_BASE.getPath());
    }

    @Override
    public String getValidPathAbsoluteFoto() {
        return getValidPathAbsolute(PathPhoto.JUGADOR_BASE.getPath());
    }

    private String getValidPathAbsolute(String base) {
        // Creating the directory to store file
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO)
                + base;
        File dir = new File(pathFull);
        if (!dir.exists())
            dir.mkdirs();

        return dir.getAbsolutePath()
                + File.separator;
    }

    @Override
    public boolean writeLogo(byte[] file, String path) throws IOException {
        return storageImage.writeLogo(file, path);
    }

    @Override
    public boolean writeFoto(byte[] file, String path) throws IOException {
        return storageImage.writeFoto(file, path);
    }

    @Override
    public void deleteLogo(String logo) {
        storageImage.deleteLogo(logo);
    }

    @Override
    public void deleteFoto(String foto) {
        storageImage.deleteFoto(foto);
    }
}
