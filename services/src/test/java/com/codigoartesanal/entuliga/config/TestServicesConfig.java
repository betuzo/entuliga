package com.codigoartesanal.entuliga.config;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by betuzo on 24/03/15.
 */
@Configuration
@Import({ServicesConfig.class, PersistenceConfig.class})
public class TestServicesConfig {

    @Test
    public void testInit(){
        Object listTipoInformacion = null;
        assert(listTipoInformacion==null);
    }

}
