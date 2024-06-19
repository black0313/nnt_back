package com.example.nnt_project.controller;

import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.UserService;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(new ApiResponse("User found", true, user)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("User not found", false)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(new ApiResponse("User saved successfully", true, savedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser)
                .map(user -> ResponseEntity.ok(new ApiResponse("User updated successfully", true, user)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("User not found", false)));
    }
}
