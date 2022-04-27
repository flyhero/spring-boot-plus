package com.github.flyhero.demo.test.exception;

/**
 * 自定义异常
 */
public class NotExistException extends RuntimeException{
    private Errors errors = null;

    public NotExistException(Errors errors) {
        this.errors = errors;
    }

    public NotExistException(String message) {
        super(message);
    }

    public NotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistException(Throwable cause) {
        super(cause);
    }

    public NotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Errors getErrors() {
        return errors;
    }
}
