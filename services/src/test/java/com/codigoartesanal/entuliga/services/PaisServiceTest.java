package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.ServicesConfig;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 12/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfig.class, PersistenceConfig.class})
public class PaisServiceTest {
    @Autowired
    PaisService paisService;

    @Test
    public void testListPais() {
        List<Map<String, Object>> paises = paisService.listPais();
        Assert.assertNotNull(paises);
    }
}
