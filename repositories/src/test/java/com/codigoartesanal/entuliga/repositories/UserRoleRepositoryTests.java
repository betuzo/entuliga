package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.PersistenceTestConfig;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.model.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by betuzo on 25/01/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class})
public class UserRoleRepositoryTests {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void testFindByNombreOficial() {
        String username = "jsoto";
        User user = new User();
        user.setUsername(username);
        Set<UserRole> userRole = userRoleRepository.findAllByUser(user);
        Assert.assertNotNull(userRole);
    }
}