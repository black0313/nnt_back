package com.example.nnt_project.payload;

import com.example.nnt_project.entity.Address;
import com.example.nnt_project.entity.Truck;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private UUID id;
    private String driverName;
    private String driverPhone;
    private String driverEmail;
    private AddressDto addressDto;
    private String zipCode;
    private boolean licenceNo;
    private Timestamp DateOfBirth;
    private UUID truckId;
}
