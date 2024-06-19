package com.example.nnt_project.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),

    ADMIN(Set.of(
            Permissions.ADMIN_CREATE,
            Permissions.ADMIN_DELETE,
            Permissions.ADMIN_UPDATE,
            Permissions.ADMIN_READ,
            Permissions.MANAGER_CREATE,
            Permissions.MANAGER_UPDATE,
            Permissions.MANAGER_READ,
            Permissions.MANAGER_DELETE
    )),
    MANAGER(Set.of(
            Permissions.MANAGER_CREATE,
            Permissions.MANAGER_UPDATE,
            Permissions.MANAGER_READ,
            Permissions.MANAGER_DELETE
    )),
    DISPATCHER(Set.of(
            Permissions.DISPATCHER_CREATE,
            Permissions.DISPATCHER_UPDATE,
            Permissions.DISPATCHER_READ,
            Permissions.DISPATCHER_DELETE
    ));

    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
