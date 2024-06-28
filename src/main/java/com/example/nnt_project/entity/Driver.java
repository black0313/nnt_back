package com.example.nnt_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String phone;
    private String passport;
    private boolean expires;

    @OneToOne
    private FileData fileData;

    @ManyToOne
    Truck truck;
}
