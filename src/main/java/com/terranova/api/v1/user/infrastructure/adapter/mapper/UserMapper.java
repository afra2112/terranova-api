package com.terranova.api.v1.user.infrastructure.adapter.mapper;

import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserEntity toEntity(User user){
        return UserEntity.builder()
                .userId(user.userId())
                .identification(user.identification())
                .names(user.names())
                .lastName(user.lastName())
                .email(user.email())
                .password(user.password())
                .phoneNumber(user.phoneNumber())
                .birthday(user.birthday())
                .registerDate(user.registerDate())
                .profilePicture(user.profilePicture())
                .roles(user.rolesIds())
                .userScore(user.userScore())
                .refreshTokenEntities(user.refreshTokenIds())
                .build();
    }

    public User toDomain(UserEntity entity){
        return new User(
                entity.getUserId(),
                entity.getIdentification(),
                entity.getNames(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhoneNumber(),
                entity.getBirthday(),
                entity.getRegisterDate(),
                entity.getProfilePicture(),
                entity.getRoles().stream().map(Role::getRoleId).toList(),
                entity.getUserScore(),
                entity.getRefreshTokenEntities().stream().map(RefreshTokenEntity::getRefreshTokenId).toList()
        );
    }
}
