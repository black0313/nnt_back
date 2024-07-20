package com.example.nnt_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrokerDto {
    private UUID id;
    private String CustomerName;
    private AddressDto addressDto;
    private String phone;
    private String primaryContact;
    private String mcAndFf;
    private Boolean isBroker;
}
