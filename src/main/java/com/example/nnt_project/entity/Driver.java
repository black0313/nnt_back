package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver extends AbsEntity {

    private String driverName;
    private String driverPhone;
    private String driverEmail;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private  String zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment licenceNo;

    private Timestamp DateOfBirth;

    @ManyToOne
    private Truck truck;
}
