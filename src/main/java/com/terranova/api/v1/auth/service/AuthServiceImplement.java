package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.dto.*;
import com.terranova.api.v1.auth.entity.RefreshToken;
import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import com.terranova.api.v1.common.exception.BusinessException;
import com.terranova.api.v1.security.CustomUserDetails;
import com.terranova.api.v1.auth.security.JwtUtil;
import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.role.repository.RoleRepository;
import com.terranova.api.v1.user.entity.User;
import com.terranova.api.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            Object principal = authentication.getPrincipal();

            if (!(principal instanceof CustomUserDetails userDetails)) {
                throw new AuthenticationServiceException("Expected CustomUserDetails but got: " + principal.getClass());
            }

            User user = userDetails.getUser();

            List<RoleEnum> roles = user.getRoles().stream().map(Role::getRoleName).toList();

            String accessToken = jwtUtil.generateToken(user.getIdentification(), roles);
            String refreshToken = refreshTokenService.create(user);

            return new AuthResponse(accessToken, refreshToken);

        }catch (BadCredentialsException ex){
            throw new BusinessException(ErrorCodeEnum.INVALID_CREDENTIALS);
        }
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailOrIdentification(request.email(), request.identification())){
            throw new BusinessException(ErrorCodeEnum.USER_ALREADY_EXISTS, "You already have an account with that email or identification, please sign in.");
        }

        User newUser = userRepository.save(buildNewUser(request));

        List<RoleEnum> rolesEnum = newUser.getRoles().stream()
                .map(role -> RoleEnum.ROLE_BUYER).toList();
        String accesToken = jwtUtil.generateToken(newUser.getIdentification(), rolesEnum);
        String refreshToken = refreshTokenService.create(newUser);

        return new AuthResponse(accesToken, refreshToken);
    }

    private User buildNewUser(RegisterRequest request){
        LocalDate birthday = validateBirthDate(request.birthday());

        User user = new User();
        user.setNames(request.names());
        user.setLastName(request.lastName());
        user.setBirthday(birthday);
        user.setIdentification(request.identification());
        user.setRegisterDate(LocalDateTime.now());
        user.setPhoneNumber(request.phoneNumber());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRegisterDate(LocalDateTime.now());
        Role role = roleRepository.findByRoleName(RoleEnum.ROLE_BUYER)
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "buyer Role not found."));
        user.setRoles(List.of(role));

        return user;
    }

    private LocalDate validateBirthDate(LocalDate date){
        int age = Period.between(date, LocalDate.now()).getYears();

        if (age < 18){
            throw new BusinessException(ErrorCodeEnum.INVALID_BIRTH_DATE, "You must have al least 18 years of age");
        }
        return date;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.validate(request.refreshToken());

        User user = refreshToken.getUser();
        List<RoleEnum> roles = user.getRoles().stream().map(Role::getRoleName).toList();

        String newAccessToken = jwtUtil.generateToken(user.getIdentification(), roles);
        String newRefreshToken = refreshTokenService.rotate(refreshToken);

        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }

    @Override
    public void logout(String refreshToken) {
        if (refreshToken == null){
            throw new BusinessException(ErrorCodeEnum.NULL_REFRESH_TOKEN, "The given refresh token is null.");
        }
        refreshTokenService.invalidate(refreshToken);
    }
}
