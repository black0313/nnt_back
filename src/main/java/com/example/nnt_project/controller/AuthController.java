package com.example.nnt_project.controller;

import com.example.nnt_project.auth.AuthenticationRequest;
import com.example.nnt_project.auth.AuthenticationResponse;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = service.authenticate(request);
            return ResponseEntity.ok(new ApiResponse("Authentication successful", true, response));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new ApiResponse("Authentication failed: Invalid credentials", false));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Authentication failed: Internal server error", false));
        }
    }
}
