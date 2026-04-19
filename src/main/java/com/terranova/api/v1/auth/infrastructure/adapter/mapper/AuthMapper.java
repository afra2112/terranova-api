package com.terranova.api.v1.auth.infrastructure.adapter.mapper;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.model.RefreshToken;
import com.terranova.api.v1.auth.domain.model.UserCredential;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.terranova.api.v1.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthMapper {

    private final PasswordEncoder passwordEncoder;

    public UserCredential toUserCredential(AuthRequest request){
        return new UserCredential(
                request.email(),
                request.password()
        );
    }

    public AuthResponse toAuthResponse(AuthenticatedCredentials authenticatedCredentials){
        return new AuthResponse(
                authenticatedCredentials.accessToken(),
                authenticatedCredentials.refreshToken()
        );
    }

    public RefreshToken fromRefreshTokenEntityToRefreshToken(RefreshTokenEntity refreshTokenEntity){
        return new RefreshToken(
                refreshTokenEntity.getRefreshTokenId(),
                refreshTokenEntity.getToken(),
                refreshTokenEntity.getExpiresAt(),
                refreshTokenEntity.isExpired(),
                refreshTokenEntity.getUserIdentification()
        );
    }

    public RefreshTokenEntity fromRefreshTokenToRefreshTokenEntity(RefreshToken refreshToken){
        return new RefreshTokenEntity(
                refreshToken.refreshTokenId(),
                refreshToken.token(),
                refreshToken.expiresAt(),
                refreshToken.isExpired(),
                refreshToken.userIdentification()
        );
    }

    public NewUserDomain fromRequestToNewUserDomain(RegisterRequest request){
        return new NewUserDomain(
                null,
                request.identification(),
                request.names(),
                request.lastName(),
                request.email(),
                request.password(),
                request.phoneNumber(),
                request.birthday(),
                LocalDateTime.now(),
                null,
                List.of("ROLE_BUYER"),
                0,
                new ArrayList<>()
        );
    }

    public User fromUserAuthDomainToUserDomain(NewUserDomain newUserDomain){
        return new User(
                newUserDomain.userId(),
                newUserDomain.identification(),
                newUserDomain.names(),
                newUserDomain.lastName(),
                newUserDomain.email(),
                passwordEncoder.encode(newUserDomain.password()),
                newUserDomain.phoneNumber(),
                newUserDomain.birthday(),
                newUserDomain.registerDate(),
                newUserDomain.profilePicture(),
                newUserDomain.roles(),
                newUserDomain.userScore(),
                newUserDomain.refreshTokenIds()
        );
    }
}
