package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse login(AuthRequest request);

    AuthResponse register(RegisterRequest request);

    RefreshTokenResponse refreshToken (RefreshTokenRequest request);

    void logout(String refreshToken);
}
