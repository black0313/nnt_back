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

    @ManyToOne
    private Broker broker;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Facility facility;

    @ManyToOne
    private Trailers trailers;

    @ManyToOne
    private Dispatchers dispatchers;
}
