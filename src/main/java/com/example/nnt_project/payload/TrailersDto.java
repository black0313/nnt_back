package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrailersDto {
    private UUID id;
    private String trailerNumber;
    private String trailerType;
    private String licencePlate;
    private String description;
    private Timestamp expiryDate;
    private boolean active;
}
