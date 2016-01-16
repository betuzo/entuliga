package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import com.codigoartesanal.entuliga.services.StorageImage;
import com.codigoartesanal.entuliga.services.google.GoogleCloudStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by betuzo on 15/01/16.
 */
@Service
@Profile("google")
public class StorageGoogleCloudImpl implements StorageImage {

    static final String PROPERTY_STATIC_GOOGLE_BUCKET_NAME = "entuliga.service.bucketName";
    static final String PROPERTY_STATIC_GOOGLE_IMAGE_CONTENT_TYPE = "image/*";

    @Resource
    Environment env;

    @Override
    public boolean writeLogo(byte[] file, String logo) {
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO) +
                PathPhoto.EQUIPO_BASE.getPath() + logo;
        return writeFile(file, pathFull);
    }

    @Override
    public boolean writeFoto(byte[] file, String foto) {
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO) +
                PathPhoto.JUGADOR_BASE.getPath() + foto;
        return writeFile(file, pathFull);
    }

    @Override
    public void deleteLogo(String logo) {
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO)
                + PathPhoto.EQUIPO_BASE.getPath() + logo;
        deleteFile(pathFull);
    }

    @Override
    public void deleteFoto(String foto) {
        String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO)
                + PathPhoto.JUGADOR_BASE.getPath() + foto;
        deleteFile(pathFull);
    }

    private void deleteFile(String path){
        try {
            GoogleCloudStorage.deleteObject(path, env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private boolean writeFile(byte[] file, String path)  {
        try {
            GoogleCloudStorage.uploadStream(
                    path, PROPERTY_STATIC_GOOGLE_IMAGE_CONTENT_TYPE,
                    new ByteArrayInputStream(file),
                    env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
