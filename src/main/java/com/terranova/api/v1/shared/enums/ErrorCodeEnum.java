package com.terranova.api.v1.shared.enums;

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
    UNAUTHORIZED("AUTH-005", HttpStatus.UNAUTHORIZED, "Password or Email invalid."),
    FORBIDDEN("AUTH-006", HttpStatus.FORBIDDEN, "You don't have permissions to access this resource"),

    //PRODUCT
    ENTITY_NOT_FOUND("PRD-001", HttpStatus.NOT_FOUND, "Entity not found"),
    PRODUCT_TYPE_NOT_SUPPORTED("PRD-002", HttpStatus.BAD_REQUEST, "Product type not supported"),
    CLOUDINARY_ERROR("PRD-003", HttpStatus.INTERNAL_SERVER_ERROR, "Cloudinary error."),
    IMAGE_READ_ERROR("PRD-004", HttpStatus.INTERNAL_SERVER_ERROR, "Error with IO reading the file from the request."),

    //APPOINTMENT
    INVALID_TIME("APMT-001", HttpStatus.BAD_REQUEST, "End time cannot be before start time in your appointment"),

    //GENERIC
    VALIDATION_ERROR("GEN-001", HttpStatus.BAD_REQUEST, "Validation error"),
    INTERNAL_ERROR("GEN-002", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    JSON_FORMAT_ERROR("GEN-003", HttpStatus.BAD_REQUEST, "JSON request format error, verify syntaxis."),
    OPEN_FEIGN_ERROR("GEN-004", HttpStatus.INTERNAL_SERVER_ERROR, "Open Feign internal communication error");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
