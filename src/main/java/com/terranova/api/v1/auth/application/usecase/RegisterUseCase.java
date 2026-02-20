package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.shared.exception.EntityNotFoundException;
import com.terranova.api.v1.user.entity.User;
import com.terranova.api.v1.user.exception.InvalidBirthDateException;
import com.terranova.api.v1.user.exception.UserAlreadyExistsByEmailOrIdentificationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class RegisterUseCase {

    @Transactional
    AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailOrIdentification(request.email(), request.identification())){
            throw new UserAlreadyExistsByEmailOrIdentificationException("You already have an account with that email or identification, please sign in.");
        }

        User newUser = userRepository.save(buildNewUser(request));

        List<RoleEnum> rolesEnum = newUser.getRoles().stream()
                .map(role -> RoleEnum.ROLE_BUYER).toList();
        String accesToken = jwtUtil.generateToken(newUser.getIdentification(), rolesEnum);
        String refreshToken = refreshTokenService.create(newUser);

        return new AuthResponse(accesToken, refreshToken);
    }

    //TODO: implement builder design pattern
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
                .orElseThrow(() -> new EntityNotFoundException("Role", "?"));
        user.setRoles(List.of(role));

        return user;
    }

    private LocalDate validateBirthDate(LocalDate date){
        int age = Period.between(date, LocalDate.now()).getYears();

        if (age < 18){
            throw new InvalidBirthDateException("You must have al least 18 years of age");
        }
        return date;
    }
}
