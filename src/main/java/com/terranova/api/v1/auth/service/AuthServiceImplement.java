package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.dto.AuthRequest;
import com.terranova.api.v1.auth.dto.AuthResponse;
import com.terranova.api.v1.auth.dto.RefreshTokenRequest;
import com.terranova.api.v1.auth.dto.RefreshTokenResponse;
import com.terranova.api.v1.auth.entity.RefreshToken;
import com.terranova.api.v1.auth.security.JwtUtil;
import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.user.entity.User;
import com.terranova.api.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = (User) authentication.getPrincipal();
        assert user != null;
        List<RoleEnum> roles = user.getRoles().stream().map(Role::getRolName).toList();

        String accessToken = jwtUtil.generateToken(user.getIdentification(), roles);
        String refreshToken = refreshTokenService.create(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.validate(request.refreshToken());

        User user = refreshToken.getUser();
        List<RoleEnum> roles = user.getRoles().stream().map(Role::getRolName).toList();

        String newAccessToken = jwtUtil.generateToken(user.getIdentification(), roles);
        String newRefreshToken = refreshTokenService.rotate(refreshToken);

        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }

    @Override
    public void logout(String refreshToken) {
        refreshTokenService.invalidate(refreshToken);
    }
}
