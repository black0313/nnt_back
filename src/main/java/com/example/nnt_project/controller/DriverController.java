package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.entity.FileData;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.DriverService;
import com.example.nnt_project.service.FileDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;
    private final FileDataService fileDataService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()){
            return ResponseEntity.ok(new ApiResponse("Drivers not found", false));
        }
        return ResponseEntity.ok(new ApiResponse("All drivers", true, drivers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDriverById(@PathVariable UUID id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.map(value -> ResponseEntity.ok(new ApiResponse("Driver found", true, value))).orElseGet(() -> ResponseEntity.ok(new ApiResponse("Driver not found", false)));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponse> createDriver(@RequestParam("driver") String driverData,
                                                    @RequestParam("file") MultipartFile file) throws IOException {
        Driver driver = new ObjectMapper().readValue(driverData, Driver.class);
        FileData fileData = fileDataService.saveFile(file);
        driver.setFileData(fileData);
        Driver createdDriver = driverService.createDriver(driver);
        return ResponseEntity.ok(new ApiResponse("Driver created successfully", true, createdDriver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDriver(@PathVariable UUID id, @RequestBody Driver updatedDriver) {
        return driverService.updateDriver(id, updatedDriver)
                .map(driver -> ResponseEntity.ok(new ApiResponse("Driver updated successfully", true, driver)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Driver not found", false)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDriver(@PathVariable UUID id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok(new ApiResponse("Driver deleted successfully", true));
    }
}
