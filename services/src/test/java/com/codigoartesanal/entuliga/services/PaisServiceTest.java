package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 12/05/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class PaisServiceTest {
    @Autowired
    PaisService paisService;

    @Test
    public void testListPais() {
        List<Map<String, Object>> paises = paisService.listPais();
        Assert.assertNotNull(paises);
    }
}
