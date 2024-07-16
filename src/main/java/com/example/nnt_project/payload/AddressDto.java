package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private UUID id;
    private String country;
    private String city;
    private String postalCode;
    private String state;
    private String street;

    public AddressDto(String country, String city, String postalCode, String state, String street) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.street = street;
    }
}
