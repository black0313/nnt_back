package com.example.nnt_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Truck {

    @Id
    @GeneratedValue
    private UUID id;

    private String truckNumber;
    private int numberOfLoads;
    private double grossRevenue;
    private double miles;
    private double emptyMiles;
    private double revenuePerMile;

    private boolean expires;


}
