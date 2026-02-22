package com.terranova.api.v1.auth.service;

import com.terranova.api.v1.auth.infrastructure.adapter.out.jwt.RefreshTokenService;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RefreshTokenRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.auth.exception.NullRefreshTokenException;
import com.terranova.api.v1.auth.infrastructure.adapter.out.jwt.JwtUtilAdapter;
import com.terranova.api.v1.role.entity.Role;
import com.terranova.api.v1.role.enums.RoleEnum;
import com.terranova.api.v1.role.repository.RoleRepository;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.terranova.api.v1.user.exception.InvalidBirthDateException;
import com.terranova.api.v1.user.exception.UserAlreadyExistsByEmailOrIdentificationException;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthServiceImplement Unit Tests")
class  AuthServiceImplementTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtilAdapter jwtUtilAdapter;
    @Mock
    private RefreshTokenService refreshTokenService;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImplement authServiceImplement;


    @Nested
    @DisplayName("Register Users Tests")
    class RegisterUserTest {

        private Role testRole;
        private RegisterRequest testRegisterRequest;

        @BeforeEach
        void setUp(){

            this.testRole = new Role(
                    RoleEnum.ROLE_BUYER
            );

            this.testRegisterRequest = new RegisterRequest(
                    "Andres Felipe",
                    "Ramirez",
                    "1094247745",
                    "andres@gmail.com",
                    "andres1234",
                    "3102162732",
                    LocalDate.of(2007,6,5)
            );
        }

        @Test
        @DisplayName("Should create user successfully")
        void shouldCreateUserSuccesfully(){
            when(jpaUserRepository.existsByEmailOrIdentification(testRegisterRequest.email(), testRegisterRequest.identification())).thenReturn(false);
            when(passwordEncoder.encode(anyString())).thenReturn("password encoded");
            when(roleRepository.findByRoleName(RoleEnum.ROLE_BUYER)).thenReturn(Optional.of(testRole));
            when(jpaUserRepository.save(any(UserEntity.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
            when(jwtUtilAdapter.generateToken(anyString(), anyList())).thenReturn("token generated");
            when(refreshTokenService.create(any(UserEntity.class))).thenReturn("refreshToken generated");

            final AuthResponse result = authServiceImplement.register(testRegisterRequest);

            assertNotNull(result);
            assertEquals("token generated", result.accessToken());
            assertEquals("refreshToken generated", result.refreshToken());

            verify(jpaUserRepository).save(any(UserEntity.class));
            verify(roleRepository).findByRoleName(RoleEnum.ROLE_BUYER);
            verify(passwordEncoder).encode(testRegisterRequest.password());
            verify(jwtUtilAdapter).generateToken(anyString(), anyList());
            verify(refreshTokenService).create(any(UserEntity.class));
        }

        @Test
        @DisplayName("Should Throw exception when already exist user")
        void throwExceprionWhenUserAlreadyExists(){
            String email = "andres@gmail.com";
            String identification = "1094247745";

            when(jpaUserRepository.existsByEmailOrIdentification(email, identification)).thenReturn(true);

            UserAlreadyExistsByEmailOrIdentificationException exception = assertThrows(UserAlreadyExistsByEmailOrIdentificationException.class,
                    () -> authServiceImplement.register(testRegisterRequest));

            assertEquals("You already have an account with that email or identification, please sign in.", exception.getMessage());

            verify(jpaUserRepository, times(1)).existsByEmailOrIdentification(email, identification);

            verify(jpaUserRepository, times(0)).save(any(UserEntity.class));
            verifyNoInteractions(jwtUtilAdapter);
            verifyNoInteractions(refreshTokenService);
        }

        @Test
        @DisplayName("Should Throw exception when invalid age")
        void throwExceptionWhenInvalidAge(){
            testRegisterRequest = new RegisterRequest(
                    "Andres Felipe",
                    "Ramirez",
                    "1094247745",
                    "andres@gmail.com",
                    "andres1234",
                    "3102162732",
                    LocalDate.of(2010,5,5)
            );

            when(jpaUserRepository.existsByEmailOrIdentification(testRegisterRequest.email(), testRegisterRequest.identification())).thenReturn(false);

            InvalidBirthDateException exception = assertThrows(InvalidBirthDateException.class, () -> authServiceImplement.register(testRegisterRequest));

            assertEquals("You must have al least 18 years of age", exception.getMessage());

            verify(jpaUserRepository, times(0)).save(any(UserEntity.class));
            verifyNoInteractions(jwtUtilAdapter);
            verifyNoInteractions(refreshTokenService);
        }
    }

    @Nested
    @DisplayName("Login User Tests")
    class LoginUserTest{

        private AuthRequest testAuthRequest;

        @BeforeEach
        void setUp(){
            this.testAuthRequest = new AuthRequest(
                    "andres@gmail.com",
                    "andres1234"
            );
        }

//        @Test
//        @DisplayName("Should Throw Exception if principal is not a CustomUserDetails object")
//        void shouldThrowExceptionIfCastingIncorrect(){
//            //MOCK PART
//            Authentication authentication = mock(Authentication.class);
//
//            when(authentication.getPrincipal()).thenReturn("Im not a Principal Object");
//            when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
//
//            AuthenticationServiceException exception = assertThrows(AuthenticationServiceException.class, () -> authServiceImplement.login(testAuthRequest));
//
//            assertEquals("Expected CustomUserDetails but got: " + Objects.requireNonNull(authentication.getPrincipal()).getClass(), exception.getMessage());
//
//            verifyNoInteractions(jwtUtilAdapter);
//            verifyNoInteractions(refreshTokenService);
//        }
//    }

    @Nested
    @DisplayName("Refresh Token Tests")
    class RefreshTokenEntityTests {

        @Test
        @DisplayName("Should Generate token even if throw Exception")
        void shouldPropagateExceptionAndCreateToken(){
            // 1. Arrange (Preparar el escenario)
            UserEntity mockUser = new UserEntity();
            mockUser.setIdentification("12345");
            mockUser.setRoles(List.of(new Role(RoleEnum.ROLE_BUYER)));

            RefreshTokenEntity mockRefreshTokenEntity = new RefreshTokenEntity();
            mockRefreshTokenEntity.setUser(mockUser);

            when(refreshTokenService.validate(anyString())).thenReturn(mockRefreshTokenEntity);
            when(jwtUtilAdapter.generateToken(anyString(), anyList())).thenReturn("new-access-token");
            when(refreshTokenService.rotate(any(RefreshTokenEntity.class)))
                    .thenThrow(new RuntimeException("Database connection failed"));

            RuntimeException exception = assertThrows(RuntimeException.class, () ->
                    authServiceImplement.refreshToken(new RefreshTokenRequest("old-token"))
            );

            assertEquals("Database connection failed", exception.getMessage());

            verify(refreshTokenService).validate("old-token");
            verify(jwtUtilAdapter).generateToken(eq("12345"), anyList());

            verify(refreshTokenService).rotate(mockRefreshTokenEntity);
        }

        @Test
        @DisplayName("Should throw Exception if refresh token is null")
        void shouldTrowNullRefreshTokenException(){

            NullRefreshTokenException exception = assertThrows(NullRefreshTokenException.class, () -> authServiceImplement.logout(null));
            assertEquals("The given refresh token is null.", exception.getMessage());
        }

        @Test
        @DisplayName("Should delete refreshToken successfuly")
        void shouldDeleteRefreshTokenSuccessfully(){
            String token = "token1234";

            authServiceImplement.logout(token);

            verify(refreshTokenService, times(1)).invalidate(token);
        }
    }
}