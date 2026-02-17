package com.terranova.api.v1.common.exception;

import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiError buildApiError(ErrorCodeEnum codeEnum, String message, int status, HttpServletRequest request){
        return new ApiError(
                codeEnum,
                message,
                status,
                request.getRequestURI(),
                LocalDateTime.now()
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
                        request
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
                        request
                ));
    }
}
