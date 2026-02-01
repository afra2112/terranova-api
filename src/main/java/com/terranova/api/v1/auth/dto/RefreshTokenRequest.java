package com.terranova.api.v1.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull
        @NotBlank
        String refreshToken
) { }
