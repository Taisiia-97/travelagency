package com.taisiia.travelagency.enums;

public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_ORDER("user:order");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
