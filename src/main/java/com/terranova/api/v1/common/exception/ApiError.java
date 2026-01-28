package com.terranova.api.v1.common.exception;

import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import java.time.LocalDateTime;

public class ApiError {

    ErrorCodeEnum code;
    String message;
    LocalDateTime timestamp;

    public ApiError(ErrorCodeEnum code, String message){
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
