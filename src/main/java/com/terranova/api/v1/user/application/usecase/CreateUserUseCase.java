package com.terranova.api.v1.user.application.usecase;

import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

public class CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    public User createUser(User user){
        return userRepositoryPort.save(user);
    }
}
