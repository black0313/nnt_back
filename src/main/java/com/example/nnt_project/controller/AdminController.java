package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @CheckPermission("GET_ADDRESS")
    @GetMapping
    public ResponseEntity<ApiResponse> getAdminInfo() {
        return ResponseEntity.ok(new ApiResponse("Admin get method", true));
    }

    @CheckPermission("ADD_ADDRESS")
    @PostMapping
    public ResponseEntity<ApiResponse> createAdminResource() {
        return ResponseEntity.ok(new ApiResponse("Admin post method", true));
    }

    @CheckPermission("ADD_ADDRESS")
    @PutMapping
    public ResponseEntity<ApiResponse> updateAdminResource() {
        return ResponseEntity.ok(new ApiResponse("Admin put method", true));
    }

    @CheckPermission("ADD_ADDRESS")
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteAdminResource() {
        return ResponseEntity.ok(new ApiResponse("Admin delete method", true));
    }
}
