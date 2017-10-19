package com.narwhal.basics.core.rest.exceptions;

/**
 * Exception launched when some entity already exist in the datastore, and
 * shouldn't be there
 * 
 * @author Tomas de Priede
 */
public class EntityAlreadyExistsException extends ApplicationException {

    /**
     * Build an application exception with a custom message and a nested
     * Throwable cause
     *
     * @param message
     * @param t
     */
    public EntityAlreadyExistsException(final String message, final Throwable t) {
        super(message, t);
    }

    /**
     * Build an exception with a custom message
     *
     * @param message
     */
    public EntityAlreadyExistsException(final String message) {
        super(message);
    }
}
