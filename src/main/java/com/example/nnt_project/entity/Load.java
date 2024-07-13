package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Load extends AbsEntity {


    private String internalLoadNumber;
    private String loadNumber;
    private String unitNumber;
    private boolean assigedTrailer;

    private double rate;
    private double deadHead;
    private double loadedMile;
    private double ratePerMile;
    private String note;

    @OneToOne
    Broker broker;

    @OneToOne
    Driver driver;

    @OneToOne
    Dispatchers dispatchers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "load_id")
    private List<Shipper> shippers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "load_id")
    private List<Consignee> consignees;
}
