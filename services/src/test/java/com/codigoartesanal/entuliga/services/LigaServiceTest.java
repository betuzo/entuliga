package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.ServicesConfig;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 12/05/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfig.class, PersistenceConfig.class})
public class LigaServiceTest {

    @Autowired
    LigaService ligaService;

    @Test
    public void testCreateLiga() {
        Map<String, String> ligaMap = new HashMap<>();
        ligaMap.put(LigaService.PROPERTY_NOMBRE, "");
        ligaMap.put(LigaService.PROPERTY_NOMBRE_COMPLETO, "");
        ligaMap.put(LigaService.PROPERTY_TELEFONO, "");
        ligaMap.put(LigaService.PROPERTY_CALLE, "");
        ligaMap.put(LigaService.PROPERTY_NO_EXTERIOR, "");
        ligaMap.put(LigaService.PROPERTY_NO_INTERIOR, "");
        ligaMap.put(LigaService.PROPERTY_CODIGO_POSTAL, "");
        ligaMap.put(LigaService.PROPERTY_LATITUDE, "20.229198");
        ligaMap.put(LigaService.PROPERTY_LONGITUDE, "-99.204637");
        ligaMap.put(LigaService.PROPERTY_COLONIA_ID, "1");

        User user = new User();
        user.setUsername("jsoto");
        Liga liga = ligaService.createLiga(ligaMap, user);
        Assert.assertNotNull(liga);
    }
}
