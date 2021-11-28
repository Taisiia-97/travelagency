package com.taisiia.travelagency.enums;

import com.taisiia.travelagency.exception.UserException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE, Permission.USER_ORDER)),
    USER(Set.of(Permission.USER_READ, Permission.USER_ORDER));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

    public static Role safeValueOf(String role) {
        try {
            return valueOf(role.toUpperCase());
        } catch (Exception e) {
            throw new UserException("This role doesn't exists");
        }
    }
}
