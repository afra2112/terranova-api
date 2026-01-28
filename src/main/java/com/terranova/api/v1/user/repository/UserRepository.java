package com.terranova.api.v1.user.repository;

import com.terranova.api.v1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserId(UUID userId);

    Optional<User> findByEmail(String email);

    Optional<User> findByIdentification(String identification);

    boolean existsByEmail(String email);

    boolean existsByIdentification(String cedula);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);

    Optional<User> findByResetToken(String resetToken);
}
