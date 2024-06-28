package com.example.nnt_project.service;

import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;

    public Truck saveTruck(Truck truck) {
        return truckRepository.save(truck);
    }

    public Optional<Truck> getTruckById(UUID id) {
        return truckRepository.findById(id);
    }

    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    public void deleteTruck(UUID id) {
        truckRepository.deleteById(id);
    }

    public Optional<Truck> updateTruck(UUID id, Truck updatedTruck) {
        return truckRepository.findById(id)
                .map(existingTruck -> {
                    updatedTruck.setId(existingTruck.getId());
                    return truckRepository.save(updatedTruck);
                });
    }
}
