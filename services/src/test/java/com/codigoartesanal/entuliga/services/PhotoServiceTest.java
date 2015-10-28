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
    public void testNullPathEquipo() {
        String path = photoService.getValidPath(null, TipoPhoto.EQUIPO);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testNullPathJugador() {
        String path = photoService.getValidPath(null, TipoPhoto.JUGADOR);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipo() {
        String path = photoService.getValidPath("", TipoPhoto.EQUIPO);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathJugador() {
        String path = photoService.getValidPath("", TipoPhoto.JUGADOR);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipo() {
        String path = photoService.getValidPath("sadasd", TipoPhoto.EQUIPO);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathJugador() {
        String path = photoService.getValidPath("asdas", TipoPhoto.JUGADOR);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testValidPathEquipo() {
        String name = "equipo-test.svg";
        String path = photoService.getValidPath(name, TipoPhoto.EQUIPO);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathJugador() {
        String name = "jugador-test.png";
        String path = photoService.getValidPath(name, TipoPhoto.JUGADOR);
        Assert.assertTrue(path.contains(name));
    }

}
