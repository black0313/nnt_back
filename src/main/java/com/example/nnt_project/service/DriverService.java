package com.example.nnt_project.service;

import com.example.nnt_project.entity.Driver;
import com.example.nnt_project.entity.Truck;
import com.example.nnt_project.mapper.AddressMapper;
import com.example.nnt_project.mapper.DriverMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.DriverDto;
import com.example.nnt_project.repository.DriverRepository;
import com.example.nnt_project.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final DriverMapper driverMapper;
    private final AddressMapper addressMapper;

    public ApiResponse saveDriver(DriverDto driverDto) {

        Driver driver = driverMapper.toDriver(driverDto);
        driver.setAddress(addressMapper.toAddress(driverDto.getAddressDto()));
        Optional<Truck> optionalTruck = truckRepository.findById(driverDto.getTruckId());
        optionalTruck.ifPresent(truck -> driver.setTruck(optionalTruck.get()));
        driverRepository.save(driver);

        return new ApiResponse("Driver saved", true);
    }

    public ApiResponse getDriverById(UUID id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        return optionalDriver.map(driver -> new ApiResponse("found", true, driverMapper.toDriverDto(driver))).orElseGet(() -> new ApiResponse("not found"));
    }

    public ApiResponse getAllDrivers() {
        List<Driver> all = driverRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("not found");
        }
        return new ApiResponse("found", true, driverMapper.toDriverDtoList(all));
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
