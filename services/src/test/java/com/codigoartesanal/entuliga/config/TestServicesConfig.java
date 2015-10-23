package com.codigoartesanal.entuliga.config;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by betuzo on 24/03/15.
 */
@Configuration
@Import({ServicesConfig.class})
public class TestServicesConfig extends PersistenceConfig{

    @Test
    public void testInit(){
        Object listTipoInformacion = null;
        assert(listTipoInformacion==null);
    }

    @Bean
    @Override
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/user.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/pais.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/estado.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/municipio.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/colonia.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/geolocation.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/equipo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/liga.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/torneo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/jugador.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/cancha.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/torneoequipo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/torneojugador.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/torneocancha.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/jornada.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/partido.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/clasificacion.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/estadistica.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/sequence.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/constraints.sql");

        return builder.setType(EmbeddedDatabaseType.H2).build();
    }
}
