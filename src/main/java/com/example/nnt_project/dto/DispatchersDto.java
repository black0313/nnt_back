package com.example.nnt_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchersDto {

    private UUID id;
    private String name;
    private int numberOfLoads;
    private double grossRevenue;
    private double netProfit;
    private int openLoads;
    private String phoneNumber;

}
