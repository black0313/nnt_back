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
    private String description;
    private Date pickDate;
    private Double weight;
    private Double value;

    @ManyToOne
    private Load load;

    private boolean shipper;
}
