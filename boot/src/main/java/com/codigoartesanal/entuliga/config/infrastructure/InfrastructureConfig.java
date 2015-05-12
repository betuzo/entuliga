package com.codigoartesanal.entuliga.config.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by betuzo on 11/05/15.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.codigoartesanal.entuliga.infrastructure" })
public class InfrastructureConfig {


}
