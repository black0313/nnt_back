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

    @CheckPermission("GET_DISPATCHERS")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable UUID id) {
        ApiResponse apiResponse = dispatchersTeamService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission("ADD_DISPATCHERS")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestParam String name, @RequestParam Long groupId) {
        ApiResponse apiResponse = dispatchersTeamService.update(id,name,groupId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }



}
