package com.example.nnt_project.payload;

import com.example.nnt_project.enums.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;

    private String name;

    private List<Permissions> permissions;

    private String description;
}
