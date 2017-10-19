package com.narwhal.basics.core.rest.exceptions;

/**
 * Root of all the applications launched by the app
 * 
 * @author Tomas de Priede
 */
public class ApplicationException extends RuntimeException {

    /**
     * Build an exception from a Throwable as a nested one.
     * 
     * @param t
     */
    public ApplicationException(final Throwable t) {
        super(t);
    }

    /**
     * Build an Application Exception
     */
    public ApplicationException() {
    }

    /**
     * Build an exception with a custom message
     * 
     * @param msg
     */
    public ApplicationException(final String msg) {
        super(msg);
    }

    /**
     * Build an application exception with a custom message and a nested
     * Throwable cause
     * 
     * @param msg
     * @param t
     */
    public ApplicationException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
