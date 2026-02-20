package com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response;

public record RefreshTokenResponse(String newAccessToken, String newRefreshToken) { }
