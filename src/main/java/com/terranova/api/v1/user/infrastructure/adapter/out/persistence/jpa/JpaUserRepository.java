package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUserId(UUID userId);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByIdentification(String identification);

    boolean existsByEmailOrIdentification(String email, String identification);
}
