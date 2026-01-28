package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.dto.AuthRequest;
import com.terranova.api.v1.auth.dto.AuthResponse;
import com.terranova.api.v1.auth.dto.RefreshTokenRequest;
import com.terranova.api.v1.auth.dto.RefreshTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse login(AuthRequest request);

    RefreshTokenResponse refreshToken (RefreshTokenRequest request);

    void logout(String refreshToken);
}
