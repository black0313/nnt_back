package com.example.nnt_project.payload;

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
    private Timestamp DateOfBirth;
    private UUID truckId;
    private UUID photoId;
}
