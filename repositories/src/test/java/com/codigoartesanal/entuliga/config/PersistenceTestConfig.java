package com.codigoartesanal.entuliga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by betuzo on 22/10/15.
 */
public class PersistenceTestConfig extends PersistenceConfig {

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
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/constraints.sql");

        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    /*
    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/user.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/pais.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/estado.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/municipio.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/colonia.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/geolocation.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/equipo.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/liga.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/torneo.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/jugador.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/cancha.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/torneoequipo.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/torneojugador.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/torneocancha.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/jornada.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/partido.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/clasificacion.sql"));


        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
    */
}
