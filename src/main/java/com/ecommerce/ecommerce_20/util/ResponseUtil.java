package com.ecommerce.ecommerce_20.util;


import com.ecommerce.ecommerce_20.core.ApiResponse;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
    public static ApiResponse getFailureMessage(Integer code, String message) {
        return ApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(code)
                .message(message)
                .build();
    }

    public static ApiResponse getSuccessMessage( Integer code , String message) {
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(code)
                .message(message)
                .build();
    }
    public static ApiResponse getSuccessMessage(Integer code, String message, Object data) {
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
    public static ApiResponse getSuccessMessage(Object data) {
        return ApiResponse.builder()
                .data(data)
                .build();

    }
}
