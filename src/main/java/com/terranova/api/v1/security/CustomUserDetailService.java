package com.terranova.api.v1.security;

import com.terranova.api.v1.shared.exception.EntityNotFoundException;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = jpaUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User", email));

        return new CustomUserDetails(user);
    }
}
