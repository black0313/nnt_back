package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperConsigneeDto {
    private UUID id;
    private Date deliveryDate;
    private String description;
    private Date pickDate;
    private Double weight;
    private Double value;
    private boolean shipper;
}
