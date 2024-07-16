package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.payload.TruckDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


public class TruckMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Truck toTruck(TruckDto dto) {
        return modelMapper.map(dto, Truck.class);
    }

    public static TruckDto toTruckDto(Truck truck) {
        return modelMapper.map(truck, TruckDto.class);
    }

    public static List<TruckDto> toTruckDtoList(List<Truck> trucks) {
        return trucks.stream()
                .map(TruckMapper::toTruckDto)
                .collect(Collectors.toList());
    }
}

