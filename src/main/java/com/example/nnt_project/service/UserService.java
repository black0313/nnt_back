package com.example.nnt_project.service;


import com.example.nnt_project.entity.Role;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.UserDto;
import com.example.nnt_project.repository.RoleRepository;
import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public ApiResponse createUser(UserDto userDto) {

        boolean exists = userRepository.existsByUsername(userDto.getUsername());
        if (exists)
            return new ApiResponse("Username already exists!");

        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
        if (optionalRole.isEmpty())
            return new ApiResponse("Role not found!");

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setRole(optionalRole.get());
        userRepository.save(user);

        return new ApiResponse("User created!", true);
    }


    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<User> updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            // Check if the new username is different and already exists
            if (!existingUser.getUsername().equals(updatedUser.getUsername()) &&
                    userRepository.findByUsername(updatedUser.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Username already exists");
            }

            existingUser.setFirstname(updatedUser.getFirstname());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setUsername(updatedUser.getUsername());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        });
    }


//    public List<DispatcherDTO> getDispatchers() {
//        return userRepository.findAll().stream()
//                .filter(user -> Role.DISPATCHER.equals(user.getRole()))
//                .map(this::convertToDispatcherDTO)
//                .collect(Collectors.toList());
//    }

//    private DispatcherDTO convertToDispatcherDTO(User user) {
//        return DispatcherDTO.builder()
//                .id(user.getId())
//                .firstname(user.getFirstname())
//                .lastname(user.getLastname())
//                .username(user.getUsername())
//                .role(user.getRole().name())
//                .build();
//    }
}
