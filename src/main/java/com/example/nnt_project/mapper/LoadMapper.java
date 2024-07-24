package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Load;
import com.example.nnt_project.payload.LoadDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadMapper {
    private final ModelMapper modelMapper;

    public Load toEntity(LoadDto loadDto) {
        return modelMapper.map(loadDto, Load.class);
    }
}
