package com.terranova.api.v1.auth.infrastructure.adapter.out.jwt;

import com.terranova.api.v1.auth.domain.model.AuthenticatedUser;
import com.terranova.api.v1.auth.domain.model.UserCredential;
import com.terranova.api.v1.auth.domain.ports.out.AuthenticationPort;
import com.terranova.api.v1.security.CustomUserDetails;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthenticationPortAdapter implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticatedUser authenticate(UserCredential credentials) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password())
            );

            Object principal = authentication.getPrincipal();
            if (!(principal instanceof CustomUserDetails userDetails)) {
                throw new AuthenticationServiceException("Expected CustomUserDetails but got: " + principal.getClass());

            }

            UserEntity user = userDetails.getUser();
            List<String> roles = user.getRoles().stream().map(role -> role.getRoleName().name()).toList();

            return new AuthenticatedUser(user.getIdentification(), roles);

        }catch (BusinessException ex){
            throw new BusinessException(ErrorCodeEnum.INVALID_CREDENTIALS);
        }

    }
}
