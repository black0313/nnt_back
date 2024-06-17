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

    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    public Optional<Truck> getTruckById(UUID id) {
        return truckRepository.findById(id);
    }

    public Truck createTruck(Truck truck) {
        return truckRepository.save(truck);
    }

    public Optional<Truck> updateTruck(UUID id, Truck updatedTruck) {
        return truckRepository.findById(id).map(truck -> {
            truck.setTruckNumber(updatedTruck.getTruckNumber());
            truck.setNumberOfLoads(updatedTruck.getNumberOfLoads());
            truck.setGrossRevenue(updatedTruck.getGrossRevenue());
            truck.setMiles(updatedTruck.getMiles());
            truck.setEmptyMiles(updatedTruck.getEmptyMiles());
            truck.setRevenuePerMile(updatedTruck.getRevenuePerMile());
            truck.setExpires(updatedTruck.isExpires());
            return truckRepository.save(truck);
        });
    }

    public void deleteTruck(UUID id) {
        truckRepository.deleteById(id);
    }
}
