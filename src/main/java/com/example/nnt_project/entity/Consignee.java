package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consignee extends AbsEntity {


    private String name;
    private String location;
    private Date date;
    private boolean showTime;
    private String description;
    private String type;
    private int quantity;
    private double weight;
    private double value;

    @ManyToOne
    private Load load;
}
