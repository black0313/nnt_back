package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.saveDriver(driver);
        return ResponseEntity.ok(new ApiResponse("Driver saved successfully", true, savedDriver));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDriverById(@PathVariable UUID id) {
        return driverService.getDriverById(id)
                .map(driver -> ResponseEntity.ok(new ApiResponse("Driver found", true, driver)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Driver not found", false)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(new ApiResponse("Drivers retrieved successfully", true, drivers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDriver(@PathVariable UUID id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok(new ApiResponse("Driver deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDriver(@PathVariable UUID id, @RequestBody Driver updatedDriver) {
        return driverService.updateDriver(id, updatedDriver)
                .map(driver -> ResponseEntity.ok(new ApiResponse("Driver updated successfully", true, driver)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Driver not found", false)));
    }
}
