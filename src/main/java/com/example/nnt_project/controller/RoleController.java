package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.RoleDto;
import com.example.nnt_project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @CheckPermission("ADD_ROLE")
    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_ROLE")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable UUID id, @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.updateRole(id,roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_ROLE")
    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        ApiResponse apiResponse = roleService.gelAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_ROLE")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable UUID id) {
        ApiResponse apiResponse = roleService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_ROLE")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable UUID id) {
        ApiResponse apiResponse = roleService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
