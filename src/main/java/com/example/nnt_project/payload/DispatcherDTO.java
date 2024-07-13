package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatcherDTO {
    private UUID id;
    private String email;
    private String phone;
    private String firstname;
    private String lastname;
    private String postalCode;
    private String city;
    private String country;
    private String state;
    private String street;
    private UUID userId;
}
