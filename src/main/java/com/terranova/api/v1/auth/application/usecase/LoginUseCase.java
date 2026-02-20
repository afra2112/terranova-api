package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.AuthenticatedUser;
import com.terranova.api.v1.auth.domain.model.UserCredential;
import com.terranova.api.v1.auth.domain.ports.out.AuthenticationPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;

public class LoginUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final RefreshTokenPort refreshTokenPort;

    public LoginUseCase(AuthenticationPort authenticationPort, TokenGeneratorPort tokenGeneratorPort, RefreshTokenPort refreshTokenPort){
        this.authenticationPort = authenticationPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
        this.refreshTokenPort = refreshTokenPort;
    }

    public AuthenticatedCredentials login(UserCredential userCredential) {

        AuthenticatedUser authenticatedUser = authenticationPort.authenticate(userCredential);

        String accessToken = tokenGeneratorPort.generateToken(authenticatedUser.identification(), authenticatedUser.roles());
        String refreshToken = refreshTokenPort.createRefreshToken(authenticatedUser.identification());

        return new AuthenticatedCredentials(accessToken, refreshToken);
    }
}
