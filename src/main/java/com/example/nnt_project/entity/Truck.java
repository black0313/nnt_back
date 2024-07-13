package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Truck extends AbsEntity {


    private String truckNumber;
    private int numberOfLoads;
    private double grossRevenue;
    private double miles;
    private double emptyMiles;
    private double revenuePerMile;

    private boolean expires;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Driver> drivers;
}
