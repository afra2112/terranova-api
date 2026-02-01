package com.terranova.api.v1.auth.exception;

public class NullRefreshTokenException extends RuntimeException {
    public NullRefreshTokenException(String message) {
        super(message);
    }
}
