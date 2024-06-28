package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveTruck(@RequestBody Truck truck) {
        Truck savedTruck = truckService.saveTruck(truck);
        return ResponseEntity.ok(new ApiResponse("Truck saved successfully", true, savedTruck));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTruckById(@PathVariable UUID id) {
        return truckService.getTruckById(id)
                .map(truck -> ResponseEntity.ok(new ApiResponse("Truck found", true, truck)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Truck not found", false)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrucks() {
        List<Truck> trucks = truckService.getAllTrucks();
        return ResponseEntity.ok(new ApiResponse("Trucks retrieved successfully", true, trucks));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTruck(@PathVariable UUID id) {
        truckService.deleteTruck(id);
        return ResponseEntity.ok(new ApiResponse("Truck deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTruck(@PathVariable UUID id, @RequestBody Truck updatedTruck) {
        return truckService.updateTruck(id, updatedTruck)
                .map(truck -> ResponseEntity.ok(new ApiResponse("Truck updated successfully", true, truck)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Truck not found", false)));
    }
}
