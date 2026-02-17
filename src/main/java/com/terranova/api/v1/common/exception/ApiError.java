package com.terranova.api.v1.common.exception;

import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record ApiError(
        ErrorCodeEnum code,
        String message,
        int status,
        String path,
        LocalDateTime timestamp,
        List<FieldApiError> errors
) {
    public ApiError(ErrorCodeEnum code, String message, int status, String path){
        this(code, message, status, path, LocalDateTime.now(), null);
    }
}
