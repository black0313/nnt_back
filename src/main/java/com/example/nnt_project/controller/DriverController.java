package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.DriverDto;
import com.example.nnt_project.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @CheckPermission("ADD_DRIVER")
    @PostMapping
    public ResponseEntity<ApiResponse> saveDriver(@RequestBody DriverDto driverDto) {
        ApiResponse apiResponse = driverService.saveDriver(driverDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_DRIVER")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable UUID id, @RequestBody DriverDto driverDto) {
        ApiResponse apiResponse = driverService.updateDriver(id, driverDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_DRIVER")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDriverById(@PathVariable UUID id) {
        ApiResponse apiResponse = driverService.getDriverById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_DRIVER")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllDrivers() {
        ApiResponse apiResponse = driverService.getAllDrivers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_DRIVER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDriver(@PathVariable UUID id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok(new ApiResponse("Driver deleted successfully", true));
    }
}
