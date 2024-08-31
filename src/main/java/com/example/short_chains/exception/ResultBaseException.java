package com.example.short_chains.exception;


/**
 * 自定义异常
 *
 * @author plumsun Created on 2023-12-03
 */
public class ResultBaseException extends RuntimeException {

    /**
     * Instantiates a new Result base exception.
     */
    public ResultBaseException() {
    }

    /**
     * Instantiates a new Result base exception.
     *
     * @param message the message
     */
    public ResultBaseException(String message) {
        super(message);
    }
}
