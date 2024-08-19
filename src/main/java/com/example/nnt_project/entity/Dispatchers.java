package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import com.example.nnt_project.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dispatchers extends AbsEntity {

    private String email;
    private String phone;
    private String firstname;
    private String lastname;

    @OneToOne
    private Address address;

    private UUID userId;

    @ManyToOne(cascade = CascadeType.ALL)
    private DispatchersTeam dispatchersTeam;

    private boolean delete;
}
