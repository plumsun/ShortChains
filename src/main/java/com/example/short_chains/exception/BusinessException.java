package com.example.short_chains.exception;

/**
 * @author plumsun on 2024/8/31
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
