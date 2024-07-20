package com.example.nnt_project.service;

import com.example.nnt_project.entity.Role;
import com.example.nnt_project.mapper.RoleMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.RoleDto;
import com.example.nnt_project.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public ApiResponse addRole(RoleDto roleDto) {
        roleRepository.save(roleMapper.convertRole(roleDto));
        return new ApiResponse("Role added successfully", true);
    }

    public ApiResponse updateRole(UUID id, RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            roleMapper.updateRoleFromDto(roleDto, role);
            roleRepository.save(role);
            return new ApiResponse("Role updated successfully", true);
        }
        return new ApiResponse("Role not found", false);
    }

    public ApiResponse gelAll() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            return new ApiResponse("No roles found", false);
        }
        return new ApiResponse("Roles found", true,
                roleMapper.convertRoleDtoList(roles));
    }

    public ApiResponse getById(UUID id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole
                .map(role -> new ApiResponse("Role found", true,
                        roleMapper.convertRoleDto(role)))
                .orElseGet(() -> new ApiResponse("no role found", false));
    }

    public ApiResponse delete(UUID id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.deleteById(id);
            return new ApiResponse("Role deleted successfully", true);
        }
        return new ApiResponse("No role found", false);
    }
}
