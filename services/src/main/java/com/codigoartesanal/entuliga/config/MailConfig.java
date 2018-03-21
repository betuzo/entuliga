package com.codigoartesanal.entuliga.config;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Created by betuzo on 26/04/15.
 */

@Configuration
@PropertySource("classpath:application.properties")
public class MailConfig {

    private static final String PROPERTY_NAME_MAIL_KEY = "mail.key";

    @Resource
    protected Environment env;

    @Bean
    public MandrillApi mandrillApi() {
        return new MandrillApi(env.getRequiredProperty(PROPERTY_NAME_MAIL_KEY));
    }

}
