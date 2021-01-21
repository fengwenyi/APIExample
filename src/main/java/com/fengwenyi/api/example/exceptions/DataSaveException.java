package com.fengwenyi.api.example.exceptions;

/**
 * @author Erwin Feng
 * @since 2021-01-21
 */
public class DataSaveException extends RuntimeException {

    public DataSaveException() {
    }

    public DataSaveException(String message) {
        super(message);
    }
}
