package com.terranova.api.v1.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "Email field is required")
        @Email
        String email,
        @NotBlank(message = "Password is required")
        String password
) { }
