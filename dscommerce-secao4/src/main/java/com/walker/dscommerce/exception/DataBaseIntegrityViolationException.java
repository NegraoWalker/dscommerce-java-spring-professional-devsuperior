package com.walker.dscommerce.exception;

public class DataBaseIntegrityViolationException extends RuntimeException {
    public DataBaseIntegrityViolationException(String message) {
        super(message);
    }
}
