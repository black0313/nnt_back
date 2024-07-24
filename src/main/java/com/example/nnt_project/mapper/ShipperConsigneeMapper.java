package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.ShipperConsignee;
import com.example.nnt_project.payload.ShipperConsigneeDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShipperConsigneeMapper {
    private final ModelMapper modelMapper;

    public ShipperConsignee toEntity(ShipperConsigneeDto shipperConsigneeDto) {
        return modelMapper.map(shipperConsigneeDto, ShipperConsignee.class);
    }

    public List<ShipperConsignee> toEntity(List<ShipperConsigneeDto> shipperConsigneeDtoList) {
        return shipperConsigneeDtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
