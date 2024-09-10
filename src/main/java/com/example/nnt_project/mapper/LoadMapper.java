package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Load;
import com.example.nnt_project.entity.ShipperConsignee;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.payload.LoadGetDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoadMapper {
    private final ModelMapper modelMapper;
    private final ShipperConsigneeMapper shipperConsigneeMapper;

    public Load toEntity(LoadDto loadDto) {
        return modelMapper.map(loadDto, Load.class);
    }

    public LoadDto toDto(Load load) {
        return modelMapper.map(load, LoadDto.class);
    }

    public List<LoadDto> loadDtoList(List<Load> loadList) {
        return loadList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LoadGetDto toGetDto(Load load, List<ShipperConsignee> shipperConsignees) {
        LoadGetDto dto = new LoadGetDto();
        dto.setLoad(load);
        dto.setShipperConsignees(shipperConsignees);
        return dto;
    }

}
