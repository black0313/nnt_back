package com.example.nnt_project.controller;

import com.example.nnt_project.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accountant")
@RequiredArgsConstructor
public class AccountantController {

    @GetMapping
    public ResponseEntity<ApiResponse> getAccountantInfo() {
        return ResponseEntity.ok(new ApiResponse("Accountant get method", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAccountantResource() {
        return ResponseEntity.ok(new ApiResponse("Accountant post method", true));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateAccountantResource() {
        return ResponseEntity.ok(new ApiResponse("Accountant put method", true));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteAccountantResource() {
        return ResponseEntity.ok(new ApiResponse("Accountant delete method", true));
    }
}
