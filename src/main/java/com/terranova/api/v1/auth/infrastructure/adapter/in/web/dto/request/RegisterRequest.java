package com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank(message = "Names are required")
        String names,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Identification is required")
        String identification,
        @NotBlank(message = "Email is required")
        @Email
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,
        @NotBlank(message = "Phone Number is required")
        String phoneNumber,
        @NotNull(message = "Your BirthDay is required")
        LocalDate birthday
) {
}
