package com.example.nnt_project.service;

import com.example.nnt_project.entity.PickupAddress;
import com.example.nnt_project.repository.PickupAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PickupAddressService {

    private final PickupAddressRepository pickupAddressRepository;

    public List<PickupAddress> getAllPickupAddresses() {
        return pickupAddressRepository.findAll();
    }

    public Optional<PickupAddress> getPickupAddressById(UUID id) {
        return pickupAddressRepository.findById(id);
    }

    public PickupAddress savePickupAddress(PickupAddress pickupAddress) {
        return pickupAddressRepository.save(pickupAddress);
    }

    public void deletePickupAddress(UUID id) {
        pickupAddressRepository.deleteById(id);
    }

    public Optional<PickupAddress> updatePickupAddress(UUID id, PickupAddress updatedPickupAddress) {
        return pickupAddressRepository.findById(id).map(existingPickupAddress -> {
            existingPickupAddress.setAddress(updatedPickupAddress.getAddress());
            return pickupAddressRepository.save(existingPickupAddress);
        });
    }
}
