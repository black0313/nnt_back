package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Role;
import com.example.nnt_project.payload.RoleDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper modelMapper;

    public Role convertRole(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    public RoleDto convertRoleDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    public List<RoleDto> convertRoleDtoList(List<Role> roleList) {
        return roleList.stream()
                .map(this::convertRoleDto)
                .collect(Collectors.toList());
    }
    public void updateRoleFromDto(RoleDto roleDto, Role role) {
        modelMapper.map(roleDto, role);
    }
}
