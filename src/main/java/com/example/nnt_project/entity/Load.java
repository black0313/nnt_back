package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Load extends AbsEntity {

    private String internalLoadNumber;

    @ManyToOne
    private Broker broker;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Trailers trailers;

    @ManyToOne
    private Dispatchers dispatchers;

    @ManyToOne
    private DispatchersTeam dispatchersTeam;

    private Boolean delete = false;

    private double deadHead;

    private double loadMile;

    private double totalRide;

    private double perMile;
}
