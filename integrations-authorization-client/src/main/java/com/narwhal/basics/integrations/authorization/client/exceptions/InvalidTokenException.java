package com.narwhal.basics.integrations.authorization.client.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.UnauthorizedException;

public class InvalidTokenException extends UnauthorizedException {
    public InvalidTokenException(Throwable t) {
        super("Invalid token", t);
    }
}
