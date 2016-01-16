package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PathWeb;
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

    @Autowired
    PathWeb pathWeb;

    @Override
    public String getValidPathWebFoto(String path) {
        return pathWeb.getValidPathWebFoto(path);
    }

    @Override
    public String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica) {
        return pathWeb.getValidPathWebLogo(path, origenEstadistica);
    }

    @Override
    public String getValidNameLogo(String path, Long idEquipo) {
        String extension=path.substring(path.lastIndexOf("."));
        return idEquipo + extension;
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
