package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.UserService;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CheckPermission("ADD_USER")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(new ApiResponse("User found", true, user)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("User not found", false)));
    }

    @CheckPermission("ADD_USER")
    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(new ApiResponse("User saved successfully", true, savedUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), false));
        }
    }

    @CheckPermission("GET_USER")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse("Users retrieved successfully", true, users));
    }

//    @GetMapping("/dispatchers")
//    public ResponseEntity<ApiResponse> getDispatchers() {
//        List<DispatcherDTO> dispatchers = userService.getDispatchers();
//        return ResponseEntity.ok(new ApiResponse("Dispatchers retrieved successfully", true, dispatchers));
//    }

    @CheckPermission("ADD_USER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully", true));
    }

    @CheckPermission("ADD_USER")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser)
                .map(user -> ResponseEntity.ok(new ApiResponse("User updated successfully", true, user)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("User not found", false)));
    }
}
