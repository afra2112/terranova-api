package com.terranova.api.v1.auth.infrastructure.adapter.mapper;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.UserCredential;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

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
}
