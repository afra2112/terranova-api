package com.terranova.api.v1.auth.application.usecase;

public class LogoutUseCase {

    public void logout(String refreshToken) {
        if (refreshToken == null){
            throw new NullRefreshTokenException("The given refresh token is null.");
        }
        refreshTokenService.invalidate(refreshToken);
    }
}
