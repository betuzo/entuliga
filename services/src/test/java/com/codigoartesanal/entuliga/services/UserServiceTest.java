package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.ServicesConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by betuzo on 8/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfig.class, PersistenceConfig.class})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindByUsername() {
        Map<String, Object> userMap = userService.findByUsername("jsoto");
        Assert.assertNotNull(userMap);
    }
}
