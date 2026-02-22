package com.terranova.api.v1.auth.infrastructure.config;

import com.terranova.api.v1.user.application.usecase.CreateUserUseCase;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthDomainConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepositoryPort){
        return new CreateUserUseCase(userRepositoryPort);
    }
}
