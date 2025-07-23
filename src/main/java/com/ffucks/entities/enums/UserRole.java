package com.ffucks.entities.enums;

public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER"),
    GUEST("GUEST");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
