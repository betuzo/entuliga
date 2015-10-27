package com.codigoartesanal.entuliga.repositories.view;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.view.Clasificacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by betuzo on 21/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@PropertySource("classpath:application.properties")
@ActiveProfiles(value = "test")
public class ClasificacionRepositoryTest {

    @Autowired
    ClasificacionRepository clasificacionRepository;

    @Test
    public void testFindAll() {
        Iterable<Clasificacion> clasificacions = clasificacionRepository.findAll();

        Assert.assertNotNull(clasificacions);
    }

    @Test
    public void testFindAllByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        Iterable<Clasificacion> clasificacions =
                clasificacionRepository.findAllByTorneoOrderByTotalGanadosDescDiferenciaPuntosDesc(torneo);

        Assert.assertNotNull(clasificacions);
    }
}
