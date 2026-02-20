package com.terranova.api.v1.auth.infrastructure.config.security;

import com.terranova.api.v1.auth.application.usecase.LoginUseCase;
import com.terranova.api.v1.auth.domain.ports.out.AuthenticationPort;
import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.domain.ports.out.TokenGeneratorPort;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthConfiguration {

    @Bean
    public LoginUseCase loginUseCase(TokenGeneratorPort tokenGeneratorPort, AuthenticationPort authenticationPort, RefreshTokenPort refreshTokenPort){
        return new LoginUseCase(
                authenticationPort,
                tokenGeneratorPort,
                refreshTokenPort
        );
    }
}
