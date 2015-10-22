package com.codigoartesanal.entuliga.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.codigoartesanal.entuliga.repositories"})
public class PersistenceConfig {

    private Database database;

    @Bean
    @Profile("production")
    public DataSource productionDataSource() {
        this.database = Database.POSTGRESQL;
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-225-199-108.compute-1.amazonaws.com:5432/d2s0bat8nnmlr7?sslmode=require");
        dataSource.setUsername("lvqqialdspisrm");
        dataSource.setPassword("lt2X_hajbja1u4RqkJbj4VgfX8");

        return dataSource;
    }

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        this.database = Database.H2;
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
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/h2/constraints.sql");

        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    @Profile("productionfirst")
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
        dataSourceInitializer.setDataSource(devDataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.codigoartesanal.entuliga.model");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(this.database);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
