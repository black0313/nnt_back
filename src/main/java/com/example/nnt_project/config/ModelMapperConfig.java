package com.example.nnt_project.config;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.payload.DriverDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Custom PropertyMap bilan ignore qilishni sozlash
        modelMapper.addMappings(new PropertyMap<DriverDto, Driver>() {
            @Override
            protected void configure() {
                skip(destination.getTruck());
                skip(destination.getAddress());
            }
        });

        return modelMapper;
    }


}

