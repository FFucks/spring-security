package com.ffucks.dtos;

import com.ffucks.entities.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
