package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import com.terranova.api.v1.user.infrastructure.adapter.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.toDomain(jpaUserRepository.save(userMapper.toEntity(user)));
    }
}
