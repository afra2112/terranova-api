package com.terranova.api.v1.auth.exception;

public class InvalidCredentialsExeption extends RuntimeException {
    public InvalidCredentialsExeption(String message) {
        super(message);
    }
}
