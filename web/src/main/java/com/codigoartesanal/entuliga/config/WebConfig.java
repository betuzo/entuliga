package com.codigoartesanal.entuliga.config;

import com.codigoartesanal.entuliga.services.PathWebService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * Created by betuzo on 28/11/14.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


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
            .addResourceLocations("file:" + env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO), "classpath:/static/img/photo/");
    }
}