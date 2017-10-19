package com.narwhal.basics.core.rest.utils.pbkdf2.exception;

/**
 * @author Tomas de Priede
 */
public class PBKDF2EncodingException extends RuntimeException {

    public PBKDF2EncodingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
