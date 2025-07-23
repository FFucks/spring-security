package com.ffucks.dtos;


public record AuthenticationDTO(String login, String password) {

    public AuthenticationDTO {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}
