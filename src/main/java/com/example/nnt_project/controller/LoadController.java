package com.example.nnt_project.controller;

import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/load")
@RequiredArgsConstructor
public class LoadController {

    private final LoadService loadService;

    @PostMapping
    public ResponseEntity<?>  create (@RequestBody LoadDto loadDto) {
        ApiResponse apiResponse = loadService.create(loadDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse apiResponse = loadService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable UUID id) {
        ApiResponse apiResponse = loadService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id) {
        ApiResponse apiResponse = loadService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
