package com.narwhal.basics.core.rest.exceptions;

/**
 * Root exception of datastore errors
 * 
 * @author Tomas de Priede
 */
public class DatastoreException extends ApplicationException {

    /**
     * Build an exception from a Throwable as a nested one.
     *
     * @param t
     */
    public DatastoreException(final Throwable t) {
        super(t);
    }

    /**
     * Build an exception with a custom message
     *
     * @param msg
     */
    public DatastoreException(final String msg) {
        super(msg);
    }
}
