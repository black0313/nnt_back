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


    public ApiResponse getUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> new ApiResponse("User found!", true, user)).orElseGet(() -> new ApiResponse("User not found!"));
    }

    public ApiResponse getAllUsers() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("No users found!");
        }
        return new ApiResponse("Found " + all.size() + " users!", true, all);
    }


    public ApiResponse deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse("User not found!");
        userRepository.deleteById(id);
        return new ApiResponse("User deleted!");
    }

    public ApiResponse updateUser(UUID id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse("User not found!");

        User user = optionalUser.get();
        if (!user.getUsername().equals(userDto.getUsername())
                && userRepository.existsByUsername(userDto.getUsername()))
            return new ApiResponse("Username already exists!");

        if (userDto.getRoleId()!=null) {
            Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
            optionalRole.ifPresent(user::setRole);
        }

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        userRepository.save(user);
        return new ApiResponse("User updated!", true);
    }
}
