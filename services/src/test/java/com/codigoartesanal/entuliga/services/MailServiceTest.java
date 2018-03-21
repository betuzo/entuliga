package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.config.TestConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 17/02/16.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Ignore
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void testSend() {
        Map<String, String> to = new HashMap<>();
        to.put("betotsol@gmail.com", "Roberto Olguin");

        Map<String, String> props = new HashMap<>();
        props.put("action", "Registrar");
        props.put("link", "lcalhosto:8090/#token/SDFSADF34FHF435YT67KJ45");
        mailService.sendTempleate("entuliga-signup-prepro", to, props);
    }
}
