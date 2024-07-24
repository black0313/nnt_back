package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShipperConsignee extends AbsEntity {

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

    @ManyToOne
    private Broker customsBroker;

    @ManyToOne
    private Load load;

    private boolean shipper;
}
