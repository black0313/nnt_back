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
    private final FileDataService fileDataService;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(UUID id) {
        return driverRepository.findById(id);
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Optional<Driver> updateDriver(UUID id, Driver updatedDriver) {
        return driverRepository.findById(id).map(driver -> {
            driver.setName(updatedDriver.getName());
            driver.setPhone(updatedDriver.getPhone());
            driver.setPassport(updatedDriver.getPassport());
            driver.setExpires(updatedDriver.isExpires());
            driver.setFileData(updatedDriver.getFileData());
            return driverRepository.save(driver);
        });
    }

    public void deleteDriver(UUID id) {
        driverRepository.deleteById(id);
    }
}
