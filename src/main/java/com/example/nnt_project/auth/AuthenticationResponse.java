package com.example.nnt_project.auth;

import com.example.nnt_project.enums.Permissions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String username;
    private String firstname;
    private String lastname;
    private String role;
    private List<Permissions> permissions;
}
