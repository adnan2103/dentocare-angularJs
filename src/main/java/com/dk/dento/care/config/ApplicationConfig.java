package com.dk.dento.care.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class ApplicationConfig {


    /**
     * Home directory configuration.
     */
    @Value("${home.dir}")
    private String home;


    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver resolver() {
        return new CommonsMultipartResolver();
    }

    @Bean(name = "home")
    public String getHome() {
        return home;
    }
}
