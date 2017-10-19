package com.narwhal.basics.core.rest.exceptions;

import com.google.api.server.spi.response.NotFoundException;

/**
 * Exception launched when some entity didn't exist in the datastore, and should
 * be there
 *
 * @author Tomas de Priede
 */
public class EntityNotFoundException extends NotFoundException {

    /**
     * Build an exception from a Throwable as a nested one.
     *
     * @param t
     */
    public EntityNotFoundException(final Throwable t) {
        super(t);
    }

    /**
     * Build an exception with a custom message
     *
     * @param msg
     */
    public EntityNotFoundException(final String msg) {
        super(msg);
    }
}
