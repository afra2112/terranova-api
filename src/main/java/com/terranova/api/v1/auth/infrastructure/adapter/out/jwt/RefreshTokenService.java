package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.auth.domain.ports.out.RefreshTokenPort;
import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.jparepository.JpaRefreshTokenRepository;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Component
public class RefreshTokenService implements RefreshTokenPort {

    private final UserRepository userRepository; //TODO: Connect correctly user domain
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

    @Override
    public String createRefreshToken(String userIdentification){
        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(userRepository.findByIdentification(userIdentification).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND)
        ));
        token.setExpiresAt(LocalDateTime.now().plusDays(30));

        jpaRefreshTokenRepository.save(token);

        return token.getToken();
    }

    public RefreshTokenEntity validate(String token) {
        RefreshTokenEntity refreshTokenEntity = jpaRefreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidRefreshTokenException("Invalid refresh token"));

        if (refreshTokenEntity.isExpired() || refreshTokenEntity.getExpiresAt().isAfter(LocalDateTime.now())) {
            throw new InvalidRefreshTokenException("Refresh token expired");
        }

        return refreshTokenEntity;
    }

    public String rotate(RefreshTokenEntity token) {
        jpaRefreshTokenRepository.delete(token);
        return create(token.getUser());
    }

    public void invalidate(String token) {
        jpaRefreshTokenRepository.deleteByToken(token);
    }

}
