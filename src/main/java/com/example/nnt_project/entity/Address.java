package com.example.nnt_project.entity;


import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends AbsEntity {
    private String country;
    private String city;
    private String postalCode;
    private String state;
    private String street;
}
