package com.example.nnt_project.service;

import com.example.nnt_project.entity.*;
import com.example.nnt_project.mapper.AddressMapper;
import com.example.nnt_project.mapper.DriverMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.DriverDto;
import com.example.nnt_project.repository.AttachmentRepository;
import com.example.nnt_project.repository.DriverRepository;
import com.example.nnt_project.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final AttachmentRepository attachmentRepository;

    @SneakyThrows
    public ApiResponse saveDriver(DriverDto driverDto) {

        Driver driver = driverMapper.toDriver(driverDto);
        driver.setAddress(addressMapper.toAddress(driverDto.getAddressDto()));
        Optional<Truck> optionalTruck = truckRepository.findById(driverDto.getTruckId());
        optionalTruck.ifPresent(truck -> driver.setTruck(optionalTruck.get()));

        if (driverDto.getPhotoId() != null) {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(driverDto.getPhotoId());
            if (optionalAttachment.isPresent()) {
                Attachment attachment = optionalAttachment.get();
                driver.setLicenceNo(attachment);
            }
        }
        driverRepository.save(driver);

        return new ApiResponse("Driver saved", true);
    }

    public ApiResponse updateDriver(UUID id, DriverDto driverDto) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            return new ApiResponse("Driver not found");
        }
        Driver driver = optionalDriver.get();
        driverMapper.updateDriver(driverDto, driver);
        driverRepository.save(driver);
        return new ApiResponse("Driver updated");
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
}
