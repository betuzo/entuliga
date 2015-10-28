package com.codigoartesanal.entuliga.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * Created by betuzo on 28/11/14.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String PROPERTY_STATIC_FILE_PHOTO = "entuliga.web.pathPhoto";
    @Resource
    protected Environment env;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/photo/**")
            .addResourceLocations(env.getRequiredProperty(PROPERTY_STATIC_FILE_PHOTO), "classpath:/static/img/photo/");
    }
}