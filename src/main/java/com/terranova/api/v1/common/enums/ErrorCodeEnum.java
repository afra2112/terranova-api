package com.terranova.api.v1.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    //USER
    USER_NOT_FOUND("USR-001", HttpStatus.NOT_FOUND, "User not found"),
    USER_ALREADY_EXISTS("USR-002", HttpStatus.CONFLICT, "User already exists"),
    INVALID_BIRTH_DATE("USR-003", HttpStatus.BAD_REQUEST, "Invalid birth date"),

    //AUTH
    INVALID_CREDENTIALS("AUTH-001", HttpStatus.UNAUTHORIZED, "Invalid credentials"),
    INVALID_TOKEN("AUTH-002", HttpStatus.FORBIDDEN, "Invalid token"),
    TOKEN_EXPIRED("AUTH-003", HttpStatus.FORBIDDEN, "Token expired"),
    NULL_REFRESH_TOKEN("AUTH-004", HttpStatus.BAD_REQUEST, "Refresh token is required"),

    //PRODUCT
    ENTITY_NOT_FOUND("PRD-001", HttpStatus.NOT_FOUND, "Entity not found"),

    //GENERIC
    VALIDATION_ERROR("GEN-001", HttpStatus.BAD_REQUEST, "Validation error"),
    INTERNAL_ERROR("GEN-002", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
