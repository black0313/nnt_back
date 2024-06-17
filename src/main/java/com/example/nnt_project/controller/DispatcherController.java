package com.example.nnt_project.controller;

import com.example.nnt_project.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispatcher")
@RequiredArgsConstructor
public class DispatcherController {

    @GetMapping
    public ResponseEntity<ApiResponse> getDispatcherInfo() {
        return ResponseEntity.ok(new ApiResponse("Dispatcher get method", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDispatcherResource() {
        return ResponseEntity.ok(new ApiResponse("Dispatcher post method", true));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateDispatcherResource() {
        return ResponseEntity.ok(new ApiResponse("Dispatcher put method", true));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteDispatcherResource() {
        return ResponseEntity.ok(new ApiResponse("Dispatcher delete method", true));
    }
}
