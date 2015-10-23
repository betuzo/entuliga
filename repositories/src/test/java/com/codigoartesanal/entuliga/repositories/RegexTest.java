package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.PersistenceTestConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by betuzo on 2/06/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class})
public class RegexTest {

    @Test
    public void testRegex() {
        String regex = "^(?=[A-Za-z0-9])(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])([\\w\\\\Q!¡@#$%&*()_+\\-={}\\[\\]\\/:;\"'<>,.?¿\\\\E]){8,16}$";

        String password= "Maas1d#sdgs";

        Assert.assertTrue(password.matches(regex));
    }
}
