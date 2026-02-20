package com.terranova.api.v1.auth.domain.model;

public record UserCredential(
        String email,
        String password
) {
}
