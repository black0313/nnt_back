package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverGetDto {
    private UUID id;
    private String driverName;
    private String driverPhone;
    private String driverEmail;
    private UUID addressId;
    private String zipCode;
    private boolean licenceNo;
    private Timestamp DateOfBirth;
    private UUID truckId;
}
