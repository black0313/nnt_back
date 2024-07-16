package com.example.nnt_project.service;

import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.mapper.TruckMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.TruckDto;
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

    public ApiResponse saveTruck(TruckDto truckDto) {
        Truck truck = TruckMapper.toTruck(truckDto);
        truckRepository.save(truck);

        return new ApiResponse("success", true);
    }

    public ApiResponse getTruckById(UUID id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);
        return optionalTruck.map(truck -> new ApiResponse("found", true, TruckMapper.toTruckDto(truck))).orElseGet(() -> new ApiResponse("not found", false));
    }

    public ApiResponse getAllTrucks() {
        List<Truck> all = truckRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("not found", false);
        }
        return new ApiResponse("found", true, TruckMapper.toTruckDtoList(all));
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
