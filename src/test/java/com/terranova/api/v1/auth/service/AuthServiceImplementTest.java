package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.dto.RegisterRequest;
import com.terranova.api.v1.user.exception.InvalidBirthDateException;
import com.terranova.api.v1.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class  AuthServiceImplementTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImplement authServiceImplement;

    @Test
    void shouldThrowExceptionWhenAgeInvalid(){
        RegisterRequest request = new RegisterRequest(
                "andres felipe",
                "ramirez",
                "1094247745",
                "andres@gmail.com",
                "andres1234",
                "3102162732",
                LocalDate.now().minusYears(17)
        );

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByIdentification(any())).thenReturn(false);

        assertThrows(InvalidBirthDateException.class, () -> authServiceImplement.register(request));
        verify(userRepository, never()).save(any());
    }
}