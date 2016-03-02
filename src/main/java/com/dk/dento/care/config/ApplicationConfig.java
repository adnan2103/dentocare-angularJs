package com.dk.dento.care.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ApplicationConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
