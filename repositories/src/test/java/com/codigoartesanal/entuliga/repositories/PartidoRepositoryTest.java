package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by betuzo on 04/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class})
public class PartidoRepositoryTest {

    @Autowired
    PartidoRepository partidoRepository;

    @Test
    public void testAcumulaPuntosLocal() {
        Long id = new Long(1);
        Long puntos = new Long(2);
        partidoRepository.acumulaPuntosLocal(puntos, id);
    }
}
