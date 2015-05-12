package com.codigoartesanal.entuliga;

import com.codigoartesanal.entuliga.config.infrastructure.InfrastructureConfig;
import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.config.ServicesConfig;
import com.codigoartesanal.entuliga.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.codigoartesanal.entuliga.controller" })
@Import({ PersistenceConfig.class, ServicesConfig.class, SecurityConfig.class, InfrastructureConfig.class})
@EnableAutoConfiguration
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}