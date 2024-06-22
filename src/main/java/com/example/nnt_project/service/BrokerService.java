package com.example.nnt_project.service;

import com.example.nnt_project.entity.Broker;
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

    public List<Broker> getAllBrokers() {
        return brokerRepository.findAll();
    }

    public Optional<Broker> getBrokerById(UUID id) {
        return brokerRepository.findById(id);
    }

    public Broker saveBroker(Broker broker) {
        return brokerRepository.save(broker);
    }

    public void deleteBroker(UUID id) {
        brokerRepository.deleteById(id);
    }

    public Optional<Broker> updateBroker(UUID id, Broker updatedBroker) {
        return brokerRepository.findById(id).map(existingBroker -> {
            existingBroker.setName(updatedBroker.getName());
            return brokerRepository.save(existingBroker);
        });
    }
}
