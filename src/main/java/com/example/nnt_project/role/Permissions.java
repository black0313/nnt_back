package com.example.nnt_project.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissions {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    MANAGER_DELETE("manager:delete"),

    DISPATCHER_READ("dispatcher:read"),
    DISPATCHER_UPDATE("dispatcher:update"),
    DISPATCHER_CREATE("dispatcher:create"),
    DISPATCHER_DELETE("dispatcher:delete"),

    ACCOUNTANT_READ("accountant:read"),
    ACCOUNTANT_UPDATE("accountant:update"),
    ACCOUNTANT_CREATE("accountant:create"),
    ACCOUNTANT_DELETE("accountant:delete");

    private final String permission;
}
