package com.narwhal.basics.external.firebase.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class InvalidFirebaseTokenException extends BadRequestException {

    public InvalidFirebaseTokenException(String token) {
        super("We could not reach any device with this token: " + token);
    }
}
