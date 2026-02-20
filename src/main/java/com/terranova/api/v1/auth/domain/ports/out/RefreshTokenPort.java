package com.terranova.api.v1.auth.domain.ports.out;

public interface RefreshTokenPort {

    String createRefreshToken(String identification);
}
