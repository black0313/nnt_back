package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Broker extends AbsEntity {

    private String CustomerName;

    @OneToOne
    private Address address;

    private String phone;
    private String primaryContact;
    private String mcAndFf;
    private Boolean isBroker;
}
