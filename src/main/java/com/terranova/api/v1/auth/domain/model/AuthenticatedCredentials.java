package com.terranova.api.v1.auth.domain.model;

public record AuthenticatedCredentials(
        String accessToken, String refreshToken
) { }
