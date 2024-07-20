package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Broker;
import com.example.nnt_project.payload.BrokerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrokerMapper {
    private final ModelMapper modelMapper;


    public BrokerDto brokerToDto(Broker broker) {
        return modelMapper.map(broker, BrokerDto.class);
    }

    public Broker brokerToEntity(BrokerDto brokerDto) {
        return modelMapper.map(brokerDto, Broker.class);
    }

    public List<BrokerDto> toBrokerDtoList(List<Broker> brokerList) {
        return brokerList.stream()
                .map(this::brokerToDto)
                .collect(Collectors.toList());
    }
    public void updateBrokerFromDto(BrokerDto brokerDto, Broker broker) {
        modelMapper.map(brokerDto, broker);
    }
}
