package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.ServicesConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * Created by betuzo on 21/01/16.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfig.class, PersistenceConfig.class})
public class StorageImageServiceTest {

    @Autowired
    StorageImageService storageImageService;

    @Test
    public void testWriteFileNull() {
        String serveerPath = "./src/test/resources/img/prueba.png";
        boolean result = true;
        result = storageImageService.writeImage(null, serveerPath, OriginPhoto.JUGADOR);
        Assert.assertTrue(result == false);
    }

    @Test
    public void testWriteDeleteFilePathLogoValid() {
        String name = "prueba.png";
        String serveerPath ="./src/test/resources/img/photo/equipo/";
        byte[] contenido = "Hallo World".getBytes();
        boolean result = true;
        result = storageImageService.writeImage(contenido, name, OriginPhoto.EQUIPO);
        storageImageService.deleteImage(name, OriginPhoto.EQUIPO);
        File dir = new File(serveerPath + name);
        if (dir.exists()) {
            Assert.assertTrue(false);
        } else  {
            Assert.assertTrue(result == true);
        }
    }

    @Test
    public void testWriteDeleteFilePathFotoValid() {
        String name = "prueba.png";
        String serveerPath ="./src/test/resources/img/photo/jugador/";
        byte[] contenido = "Hallo World".getBytes();
        boolean result = true;
        result = storageImageService.writeImage(contenido, name, OriginPhoto.JUGADOR);
        storageImageService.deleteImage(name, OriginPhoto.JUGADOR);
        File dir = new File(serveerPath + name);
        if (dir.exists()) {
            Assert.assertTrue(false);
        } else  {
            Assert.assertTrue(result == true);
        }
    }
}
