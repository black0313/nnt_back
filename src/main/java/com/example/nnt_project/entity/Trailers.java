package com.example.nnt_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Trailers {
    @Id
    @GeneratedValue
    private UUID id;

    private String trailerNumber;
    private int numberOfLoads;
    private double grossRevenue;
    private double revenuePerMile;

    private boolean expires;
}
