package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.TruckService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/trucks")
@RequiredArgsConstructor
@Api(value = "Truck Management System")
public class TruckController {

    private final TruckService truckService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrucks() {
        List<Truck> trucks = truckService.getAllTrucks();
        if (trucks.isEmpty()){
            return ResponseEntity.ok(new ApiResponse("Empty", false, null));
        }
        return ResponseEntity.ok(new ApiResponse("Fetched all trucks", true, trucks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTruckById(@PathVariable UUID id) {
        Optional<Truck> truck = truckService.getTruckById(id);
        if (truck.isPresent()) {
            return ResponseEntity.ok(new ApiResponse("Truck found", true, truck.get()));
        } else {
            return ResponseEntity.ok(new ApiResponse("Truck not found", false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTruck(@RequestBody Truck truck) {
        if (truck.getTruckNumber() == null || truck.getTruckNumber().isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse("Truck number is required", false));
        }
        Truck createdTruck = truckService.createTruck(truck);
        return ResponseEntity.ok(new ApiResponse("Truck created successfully", true, createdTruck));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTruck(@PathVariable UUID id, @RequestBody Truck updatedTruck) {
        return truckService.updateTruck(id, updatedTruck)
                .map(truck -> ResponseEntity.ok(new ApiResponse("Truck updated successfully", true, truck)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Truck not found", false)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTruck(@PathVariable UUID id) {
        truckService.deleteTruck(id);
        return ResponseEntity.ok(new ApiResponse("Truck deleted successfully", true));
    }
}
