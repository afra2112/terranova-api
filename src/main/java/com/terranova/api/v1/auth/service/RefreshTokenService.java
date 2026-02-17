package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.entity.RefreshToken;
import com.terranova.api.v1.auth.repository.RefreshTokenRepository;
import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import com.terranova.api.v1.common.exception.BusinessException;
import com.terranova.api.v1.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public String create(User user){
        RefreshToken token = new RefreshToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiresAt(LocalDateTime.now().plusDays(30));

        refreshTokenRepository.save(token);

        return token.getToken();
    }

    public RefreshToken validate(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.INVALID_TOKEN, "Invalid refresh token"));

        if (refreshToken.isExpired() || refreshToken.getExpiresAt().isAfter(LocalDateTime.now())) {
            throw new BusinessException(ErrorCodeEnum.TOKEN_EXPIRED, "Refresh token expired");
        }

        return refreshToken;
    }

    public String rotate(RefreshToken token) {
        refreshTokenRepository.delete(token);
        return create(token.getUser());
    }

    public void invalidate(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
