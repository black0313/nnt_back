package com.example.nnt_project.service;

import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role must not be null");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Saving user with role: " + user.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<User> updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstname(updatedUser.getFirstname());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setRole(updatedUser.getRole());
            logger.info("Updating user with role: " + updatedUser.getRole());
            return userRepository.save(existingUser);
        });
    }
}
