package com.example.nnt_project.service;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Optional<Driver> getDriverById(UUID id) {
        return driverRepository.findById(id);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public void deleteDriver(UUID id) {
        driverRepository.deleteById(id);
    }

    public Optional<Driver> updateDriver(UUID id, Driver updatedDriver) {
        return driverRepository.findById(id)
                .map(existingDriver -> {
                    updatedDriver.setId(existingDriver.getId());
                    return driverRepository.save(updatedDriver);
                });
    }
}
