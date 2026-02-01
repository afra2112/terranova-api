package com.terranova.api.v1.user.exception;

public class InvalidBirthDateException extends RuntimeException {
    public InvalidBirthDateException(String message) {
        super(message);
    }
}
