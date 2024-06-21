package com.example.nnt_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatcherDTO {

    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String role;
}
