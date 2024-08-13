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

    private Date deliveryDate;
    private Date pickDate;
    private String description;
    private Double weight;
    private Double value;

    @ManyToOne
    private Load load;

    @ManyToOne
    private PickupAddress pickupAddress;

    private boolean shipper;

    private Boolean delete = false;
}
