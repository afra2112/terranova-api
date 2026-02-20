package com.terranova.api.v1.auth.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record RefreshToken(
        UUID refreshTokenId,
        String token,
        LocalDateTime expiresAt,
        boolean isExpired,
        Long userId
) {
}
