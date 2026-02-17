package com.terranova.api.v1.common.exception;

import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiError buildApiError(ErrorCodeEnum codeEnum, String message, int status, HttpServletRequest request, List<FieldApiError> validationErrors){
        return new ApiError(
                codeEnum,
                message,
                status,
                request.getRequestURI(),
                LocalDateTime.now(),
                validationErrors
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, HttpServletRequest request){

        log.warn(
                "Business error at [{}]: {} - {}",
                request.getRequestURI(),
                ex.getErrorCodeEnum().getCode(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(ex.getErrorCodeEnum().getStatus())
                .body(buildApiError(
                        ex.getErrorCodeEnum(),
                        ex.getMessage(),
                        ex.getErrorCodeEnum().getStatus().value(),
                        request,
                        null
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request){

        log.error(
                "Internal error at: [{}]: {}",
                request.getRequestURI(),
                ex.getMessage(),
                ex
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildApiError(
                        ErrorCodeEnum.INTERNAL_ERROR,
                        ErrorCodeEnum.INTERNAL_ERROR.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        request,
                        null
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMehodArgument(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<FieldApiError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldApiError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        log.warn(
                "Validation error | method={} | path={} | errors={}",
                request.getMethod(),
                request.getRequestURI(),
                errors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(
                        ErrorCodeEnum.VALIDATION_ERROR,
                        ErrorCodeEnum.VALIDATION_ERROR.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        request,
                        errors
                ));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ApiError> handleInternalAuthError(
            InternalAuthenticationServiceException ex,
            HttpServletRequest request
    ) {

        log.error(
                "Internal authentication error | method={} | path={} | message={}",
                request.getMethod(),
                request.getRequestURI(),
                ex.getMessage(),
                ex
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildApiError(
                        ErrorCodeEnum.INTERNAL_ERROR,
                        ErrorCodeEnum.INTERNAL_ERROR.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        request,
                        null
                ));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {

        log.warn(
                "Access denied | method={} | path={}",
                request.getMethod(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(buildApiError(
                        ErrorCodeEnum.UNAUTHORIZED,
                        ErrorCodeEnum.UNAUTHORIZED.getMessage(),
                        HttpStatus.FORBIDDEN.value(),
                        request,
                        null
                ));
    }

}
