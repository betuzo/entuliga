package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import com.codigoartesanal.entuliga.services.StorageImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by betuzo on 15/01/16.
 */
@Service
@Profile({"dev", "prepro", "test"})
public class StorageLocalImpl implements StorageImage {

    @Resource
    Environment env;

    @Autowired
    PhotoService photoService;

    @Override
    public boolean writeLogo(byte[] file, String path) {
        return writeFile(file, photoService.getValidPathAbsoluteLogo() + path);
    }

    @Override
    public boolean writeFoto(byte[] file, String path)  {
        return writeFile(file, photoService.getValidPathAbsoluteFoto() + path);
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

    private boolean writeFile(byte[] file, String path)  {
        if (file == null || path == null || path.isEmpty()) {
            return false;
        }

        File serverFile = new File(path);
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            stream.write(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
