package com.example.nnt_project.payload;

import com.example.nnt_project.entity.*;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDto {

    private UUID id;
    private String internalLoadNumber;
//    private String loadNumber;
//    private String unitNumber;
//    private boolean assigedTrailer;
//
//    private double rate;
//    private double deadHead;
//    private double loadedMile;
//    private double ratePerMile;


    private UUID driverId;
    private UUID truckId;
    private UUID trailerId;
    private UUID dispatcherId;

    private List<ShipperConsigneeDto> shipperConsigneeDtoList;
}
