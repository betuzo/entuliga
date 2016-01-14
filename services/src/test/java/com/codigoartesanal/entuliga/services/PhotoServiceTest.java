package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.ServicesConfig;
import com.codigoartesanal.entuliga.model.OrigenEstadistica;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by betuzo on 28/10/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfig.class, PersistenceConfig.class})
public class PhotoServiceTest {

    @Autowired
    PhotoService photoService;

    @Test
    public void testNullPathEquipoDefault() {
        String path = photoService.getValidPathWebLogo(null, null);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testNullPathEquipoLocal() {
        String path = photoService.getValidPathWebLogo(null, OrigenEstadistica.LOCAL);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath().equals(path));
    }

    @Test
    public void testNullPathEquipoVisita() {
        String path = photoService.getValidPathWebLogo(null, OrigenEstadistica.VISITA);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_VISITA.getPath().equals(path));
    }

    @Test
    public void testNullPathJugador() {
        String path = photoService.getValidPathWebFoto(null);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipoDefault() {
        String path = photoService.getValidPathWebLogo("", null);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipoLocal() {
        String path = photoService.getValidPathWebLogo("", OrigenEstadistica.LOCAL);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipoVisita() {
        String path = photoService.getValidPathWebLogo("", OrigenEstadistica.VISITA);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_VISITA.getPath().equals(path));
    }

    @Test
    public void testEmptyPathJugador() {
        String path = photoService.getValidPathWebFoto("");
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipoDefault() {
        String path = photoService.getValidPathWebLogo("sadasd", null);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipoLocal() {
        String path = photoService.getValidPathWebLogo("sadasd", OrigenEstadistica.LOCAL);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipoVisita() {
        String path = photoService.getValidPathWebLogo("sadasd", OrigenEstadistica.VISITA);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_VISITA.getPath().equals(path));
    }

    @Test
    public void testInvalidPathJugador() {
        String path = photoService.getValidPathWebFoto("asdas");
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testValidPathEquipoDefault() {
        String name = "equipo-test.svg";
        String path = photoService.getValidPathWebLogo(name, null);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathEquipoLocal() {
        String name = "equipo-test.svg";
        String path = photoService.getValidPathWebLogo(name, OrigenEstadistica.LOCAL);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathEquipoVisita() {
        String name = "equipo-test.svg";
        String path = photoService.getValidPathWebLogo(name, OrigenEstadistica.VISITA);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathJugador() {
        String name = "jugador-test.png";
        String path = photoService.getValidPathWebFoto(name);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testGetValidPathAbsoluteLogo() {
        String path = photoService.getValidPathAbsoluteLogo();
        Assert.assertTrue(path.contains(PathPhoto.EQUIPO_BASE.getPath()));
    }

    @Test
    public void testWriteFileNull() {
        String serveerPath = "./src/test/resources/img/prueba.png";
        boolean result = true;
        try {
            result = photoService.writeFile(null, serveerPath);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(result == false);
    }

    @Test
    public void testWriteDeleteFilePathLogoValid() {
        String name = "prueba.png";
        String serveerPath ="./src/test/resources/img/photo/equipo/";
        byte[] contenido = "Hallo World".getBytes();
        boolean result = true;
        try {
            result = photoService.writeFile(contenido, serveerPath + name);
            photoService.deleteLogo(name);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
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
        try {
            result = photoService.writeFile(contenido, serveerPath + name);
            photoService.deleteFoto(name);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        File dir = new File(serveerPath + name);
        if (dir.exists()) {
            Assert.assertTrue(false);
        } else  {
            Assert.assertTrue(result == true);
        }
    }

}
