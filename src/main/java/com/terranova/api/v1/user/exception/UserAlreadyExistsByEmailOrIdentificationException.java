package com.terranova.api.v1.user.exception;

public class UserAlreadyExistsByEmailOrIdentificationException extends RuntimeException {
    public UserAlreadyExistsByEmailOrIdentificationException(String message) {
        super(message);
    }
}
