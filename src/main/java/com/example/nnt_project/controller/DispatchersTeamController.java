package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.DispatchersTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dispatchers-team")
@RequiredArgsConstructor
public class DispatchersTeamController {
    private final DispatchersTeamService dispatchersTeamService;

    @CheckPermission("ADD_DISPATCHERS")
    @PostMapping
    public ResponseEntity<?> create(@RequestParam String name) {
        ApiResponse apiResponse = dispatchersTeamService.create(name);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission("GET_DISPATCHERS")
    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse apiResponse = dispatchersTeamService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission("ADD_DISPATCHERS")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestParam String name) {
        ApiResponse apiResponse = dispatchersTeamService.update(id,name);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }



}
