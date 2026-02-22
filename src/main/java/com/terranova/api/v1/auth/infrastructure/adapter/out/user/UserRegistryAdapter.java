package com.terranova.api.v1.auth.infrastructure.adapter.out.user;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.ports.out.UserRegisterPort;
import com.terranova.api.v1.auth.infrastructure.adapter.mapper.AuthMapper;
import com.terranova.api.v1.user.application.usecase.CreateUserUseCase;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa.JpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRegistryAdapter implements UserRegisterPort {

    private final JpaUserRepository jpaUserRepository;
    private final CreateUserUseCase createUserUseCase;
    private final AuthMapper authMapper;

    @Override
    public boolean existByEmailOrIdentification(String email, String identification) {
        return jpaUserRepository.existsByEmailOrIdentification(email, identification);
    }

    @Override
    public AuthenticatedCredentials createUser(NewUserDomain newUserDomain) {
        return createUserUseCase.createUser();
    }
}
