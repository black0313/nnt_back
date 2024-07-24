package com.example.nnt_project.payload;

import com.example.nnt_project.entity.Broker;
import com.example.nnt_project.entity.Load;
import jakarta.persistence.ManyToOne;
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
    private String name;
    private String location;
    private Date date;
    private boolean showTime;
    private String description;
    private String type;
    private int quantity;
    private double weight;
    private double value;
    private String notes;
    private String poNumbers;
    private UUID customsBrokerId;
    private boolean shipper;
}
