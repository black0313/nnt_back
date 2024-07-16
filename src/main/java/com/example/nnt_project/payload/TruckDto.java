package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckDto {
    private UUID id;
    private String truckNumber;
    private String truckType;
    private String licencePlate;
    private Timestamp plateExpiry;
    private String description;
    private Boolean active;
    private boolean ownershipIsPrivate;
}
