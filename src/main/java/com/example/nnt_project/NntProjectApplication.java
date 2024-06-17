package com.example.nnt_project;

import com.example.nnt_project.auth.RegisterRequest;
import com.example.nnt_project.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.nnt_project.role.Role.ADMIN;
import static com.example.nnt_project.role.Role.MANAGER;

@SpringBootApplication
public class NntProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NntProjectApplication.class, args);
    }



}
