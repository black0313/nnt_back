package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.DriverDto;
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
    public ResponseEntity<ApiResponse> saveDriver(@RequestBody DriverDto driverDto) {
        ApiResponse apiResponse = driverService.saveDriver(driverDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDriverById(@PathVariable UUID id) {
        ApiResponse apiResponse = driverService.getDriverById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDrivers() {
        ApiResponse apiResponse = driverService.getAllDrivers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
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
