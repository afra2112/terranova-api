package com.terranova.api.v1.security;

import com.terranova.api.v1.common.exception.EntityNotFoundException;
import com.terranova.api.v1.user.entity.User;
import com.terranova.api.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User", email));

        return new CustomUserDetails(user);
    }
}
