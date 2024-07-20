package com.example.nnt_project.service;

import com.example.nnt_project.entity.Broker;
import com.example.nnt_project.mapper.BrokerMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.BrokerDto;
import com.example.nnt_project.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrokerService {

    private final BrokerRepository brokerRepository;
    private final BrokerMapper brokerMapper;

    public ApiResponse getAllBrokers() {
        List<Broker> all = brokerRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("no broker found");
        }
        return new ApiResponse("found", true, brokerMapper.toBrokerDtoList(all));
    }

    public ApiResponse getBrokerById(UUID id) {
        Optional<Broker> optionalBroker = brokerRepository.findById(id);
        return optionalBroker.map(broker -> new ApiResponse("found", true, brokerMapper.brokerToDto(broker))).orElseGet(() -> new ApiResponse("no broker found"));
    }

    public ApiResponse saveBroker(BrokerDto brokerDto) {
        brokerRepository.save(brokerMapper.brokerToEntity(brokerDto));
        return new ApiResponse("successfully saved broker", true);
    }

    public void deleteBroker(UUID id) {
        brokerRepository.deleteById(id);
    }

    public ApiResponse updateBroker(UUID id, BrokerDto brokerDto) {
        Optional<Broker> optionalBroker = brokerRepository.findById(id);
        if (optionalBroker.isPresent()) {
            Broker broker = optionalBroker.get();
            brokerMapper.updateBrokerFromDto(brokerDto, broker);
            brokerRepository.save(broker);
            return new ApiResponse("successfully updated broker", true);
        }
        return new ApiResponse("no broker found");
    }
}
