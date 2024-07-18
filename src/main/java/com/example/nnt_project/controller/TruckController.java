package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.TruckDto;
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

    @CheckPermission("ADD_TRUCK")
    @PostMapping
    public ResponseEntity<ApiResponse> saveTruck(@RequestBody TruckDto truckDto) {
        ApiResponse apiResponse = truckService.saveTruck(truckDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_TRUCK")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTruckById(@PathVariable UUID id) {
        ApiResponse apiResponse = truckService.getTruckById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_TRUCK")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrucks() {
        ApiResponse apiResponse = truckService.getAllTrucks();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_TRUCK")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTruck(@PathVariable UUID id) {
        truckService.deleteTruck(id);
        return ResponseEntity.ok(new ApiResponse("Truck deleted successfully", true));
    }

    @CheckPermission("ADD_TRUCK")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTruck(@PathVariable UUID id, @RequestBody Truck updatedTruck) {
        return truckService.updateTruck(id, updatedTruck)
                .map(truck -> ResponseEntity.ok(new ApiResponse("Truck updated successfully", true, truck)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Truck not found", false)));
    }
}
