package com.terranova.api.v1.common.exception;

import com.terranova.api.v1.user.exception.InvalidBirthDateException;
import com.terranova.api.v1.auth.exception.InvalidJwtTokenException;
import com.terranova.api.v1.user.exception.UserAlreadyExistsByEmailOrIdentificationException;
import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import com.terranova.api.v1.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ErrorCodeEnum.USER_NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ApiError> handleInvalidToken(InvalidJwtTokenException ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiError(ErrorCodeEnum.INVALID_TOKEN, ex.getMessage()));
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
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(ErrorCodeEnum.INTERNAL_ERROR, ex.getClass().getSimpleName() + " : " + ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsByEmailOrIdentificationException.class)
    public ResponseEntity<ApiError> handleUserExistsByEmailOrIdentification(UserAlreadyExistsByEmailOrIdentificationException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(ErrorCodeEnum.USER_ALREADY_EXISTS, ex.getMessage()));
    }

    @ExceptionHandler(InvalidBirthDateException.class)
    public  ResponseEntity<ApiError> handleMinimumAgeException(InvalidBirthDateException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(ErrorCodeEnum.INVALID_AGE, ex.getMessage()));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public  ResponseEntity<ApiError> handleUserNotFoundSpringException(InternalAuthenticationServiceException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ErrorCodeEnum.USER_NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(ErrorCodeEnum.INCORRECT_PASSWORD, "Ups... Incorrect password, please try again."));
    }
}
