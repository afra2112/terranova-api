package com.terranova.api.v1.common.exception;

import com.terranova.api.v1.auth.exception.NullRefreshTokenException;
import com.terranova.api.v1.auth.exception.TokenExpiredException;
import com.terranova.api.v1.user.exception.InvalidBirthDateException;
import com.terranova.api.v1.auth.exception.InvalidJwtTokenException;
import com.terranova.api.v1.user.exception.UserAlreadyExistsByEmailOrIdentificationException;
import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> buildError(HttpStatus status, ErrorCodeEnum errorCode, String message){
        return ResponseEntity
                .status(status)
                .body(new ApiError(errorCode, message));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(EntityNotFoundException ex){
        return buildError(
                HttpStatus.NOT_FOUND,
                ErrorCodeEnum.ENTITY_NOT_FOUND,
                ex.getMessage()
                );
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ApiError> handleInvalidToken(InvalidJwtTokenException ex){
        return buildError(
                HttpStatus.UNAUTHORIZED,
                ErrorCodeEnum.INVALID_TOKEN,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationSpring(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex){
        log.error("Unexpected error: ", ex);
        return buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCodeEnum.INTERNAL_ERROR,
                ex.getMessage()
        );
    }

    @ExceptionHandler(UserAlreadyExistsByEmailOrIdentificationException.class)
    public ResponseEntity<ApiError> handleUserExistsByEmailOrIdentification(UserAlreadyExistsByEmailOrIdentificationException ex){
        return buildError(
                HttpStatus.CONFLICT,
                ErrorCodeEnum.USER_ALREADY_EXISTS,
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidBirthDateException.class)
    public ResponseEntity<ApiError> handleMinimumAgeException(InvalidBirthDateException ex){
        return buildError(
                HttpStatus.BAD_REQUEST,
                ErrorCodeEnum.INVALID_AGE,
                ex.getMessage()
        );
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ApiError> handleUserNotFoundSpringException(InternalAuthenticationServiceException ex){
        return buildError(
                HttpStatus.NOT_FOUND,
                ErrorCodeEnum.USER_NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex){
        return buildError(
                HttpStatus.UNAUTHORIZED,
                ErrorCodeEnum.INCORRECT_PASSWORD,
                "Ups... Incorrect password, please try again."
        );
    }

    @ExceptionHandler(NullRefreshTokenException.class)
    public ResponseEntity<ApiError> handleNullRefreshTokenException(NullRefreshTokenException ex){
        return buildError(
                HttpStatus.BAD_REQUEST,
                ErrorCodeEnum.NULL_REFRESH_TOKEN,
                ex.getMessage()
        );
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiError> handleTokenExpiredException(TokenExpiredException ex){
        return buildError(
                HttpStatus.UNAUTHORIZED,
                ErrorCodeEnum.TOKEN_EXPIRED,
                ex.getMessage()
        );
    }
}
