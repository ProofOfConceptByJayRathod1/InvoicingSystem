package com.simform.invoicingsystem.config;

import com.simform.invoicingsystem.modelmapping.ModelMapping;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(ModelMapping.getProjectMapping());
        mapper.addMappings(ModelMapping.getProjectDetailsMapping());
        mapper.addMappings(ModelMapping.getProjectViewMapping());
        return mapper;
    }
}
