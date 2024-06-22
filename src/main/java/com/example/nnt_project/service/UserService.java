package com.example.nnt_project.service;

import com.example.nnt_project.dto.DispatcherDTO;
import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import com.example.nnt_project.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role must not be null");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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


    public List<DispatcherDTO> getDispatchers() {
        return userRepository.findAll().stream()
                .filter(user -> Role.DISPATCHER.equals(user.getRole()))
                .map(this::convertToDispatcherDTO)
                .collect(Collectors.toList());
    }

    private DispatcherDTO convertToDispatcherDTO(User user) {
        return DispatcherDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
