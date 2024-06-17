package com.example.nnt_project.controller;

import com.example.nnt_project.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping
    public ResponseEntity<ApiResponse> getManagerInfo() {
        return ResponseEntity.ok(new ApiResponse("Manager get method", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createManagerResource() {
        return ResponseEntity.ok(new ApiResponse("Manager post method", true));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateManagerResource() {
        return ResponseEntity.ok(new ApiResponse("Manager put method", true));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteManagerResource() {
        return ResponseEntity.ok(new ApiResponse("Manager delete method", true));
    }
}
