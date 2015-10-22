package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceTestConfig;
import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Torneo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by betuzo on 28/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class})
public class EquipoRepositoryTest {

    @Autowired
    EquipoRepository equipoRepository;

    @Test
    public void testFindAllNotInTorneoAndNombreContaining() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        String likename = "%ctivos%";
        List<Equipo> equipos = equipoRepository.findAllNotInTorneoAndNombreContaining(torneo, likename);
        Assert.assertNotNull(equipos);
    }
}
