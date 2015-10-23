package com.codigoartesanal.entuliga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by betuzo on 22/10/15.
 */
public class PersistenceTestConfig extends PersistenceConfig {

    @Bean
    @Override
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/user.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/pais.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/estado.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/municipio.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/colonia.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/geolocation.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/equipo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/liga.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/torneo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/jugador.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/cancha.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/torneoequipo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/torneojugador.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/torneocancha.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/jornada.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/partido.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/clasificacion.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/estadistica.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/sequence.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/ddl/constraints.sql");

        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/user.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/pais.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/estado.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/municipio.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/colonia.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/geolocation.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/equipo.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/liga.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/torneo.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/jugador.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/cancha.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/torneoequipo.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/torneojugador.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/torneocancha.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/jornada.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/partido.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/h2/dml/estadistica.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
