package com.codigoartesanal.entuliga.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/user.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/pais.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/estado.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/municipio.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/colonia.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/geolocation.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/equipo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/liga.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/torneo.sql");
        builder.addScript("classpath:/com/codigoartesanal/entuliga/scripts/constraints.sql");
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    /*@Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/pais.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/estado.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/municipio.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/com/codigoartesanal/entuliga/scripts/colonia.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }*/

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
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
