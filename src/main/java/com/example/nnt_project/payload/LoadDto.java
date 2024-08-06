package com.example.nnt_project.payload;

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
    private UUID driverId;
    private UUID truckId;
    private UUID trailerId;
    private UUID dispatcherId;
    private UUID facilityId;
    private UUID dispatcherTeamId;
    private List<ShipperConsigneeDto> shipperConsigneeDtoList;

}
