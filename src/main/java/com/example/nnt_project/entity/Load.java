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

    public void calculateRideMetrics(double loadMile, double deadHead, double totalRide) {
        this.loadMile = loadMile;
        this.deadHead = deadHead;
        this.totalRide = totalRide;

        // Umumiy marshrut va har kilometr uchun narxni hisoblash
        double fullRide = loadMile + deadHead;
        if (fullRide > 0) {
            this.perMile = totalRide / fullRide;
        } else {
            this.perMile = 0;
        }
    }
}
