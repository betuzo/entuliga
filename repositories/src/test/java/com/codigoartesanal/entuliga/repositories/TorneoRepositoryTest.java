package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.Torneo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by betuzo on 21/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class TorneoRepositoryTest {

    @Autowired
    TorneoRepository torneoRepository;

    @Test
    public void testFindAllByLiga() {
        Liga liga = new Liga();
        liga.setId(1L);
        List<Torneo> torneos = torneoRepository.findAllByLiga(liga);

        Assert.assertNotNull(torneos);
    }
}
