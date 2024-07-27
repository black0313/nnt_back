package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.payload.DriverDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DriverMapper {

    private final ModelMapper modelMapper;

    public DriverDto toDriverDto(Driver driver) {
        return modelMapper.map(driver, DriverDto.class);
    }

    public Driver toDriver(DriverDto driverDto) {
        return modelMapper.map(driverDto, Driver.class);
    }

    public List<DriverDto> toDriverDtoList(List<Driver> drivers) {
        return drivers.stream()
                .map(this::toDriverDto)
                .collect(Collectors.toList());
    }
    public void updateDriver(DriverDto driverDto, Driver driver) {
        modelMapper.map(driverDto, driver);
    }
}

