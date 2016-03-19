package com.dk.dento.care.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class ApplicationConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver resolver() {
        return new CommonsMultipartResolver();
    }
}
