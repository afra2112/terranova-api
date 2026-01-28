package com.terranova.api.v1.auth.dto;

public record RefreshTokenResponse(String newAccessToken, String newRefreshToken) { }
