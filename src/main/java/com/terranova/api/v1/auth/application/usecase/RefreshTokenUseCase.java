package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RefreshTokenRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.RefreshTokenResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;

import java.util.List;

public class RefreshTokenUseCase {

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.validate(request.refreshToken());

        UserEntity user = refreshTokenEntity.getUser();
        List<RoleEnum> roles = user.getRoles().stream().map(Role::getRoleName).toList();

        String newAccessToken = jwtUtil.generateToken(user.getIdentification(), roles);
        String newRefreshToken = refreshTokenService.rotate(refreshTokenEntity);

        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }
}
