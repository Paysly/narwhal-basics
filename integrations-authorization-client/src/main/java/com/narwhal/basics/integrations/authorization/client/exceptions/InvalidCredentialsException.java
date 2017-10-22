package com.narwhal.basics.integrations.authorization.client.exceptions;

import com.narwhal.basics.core.rest.exceptions.api.UnauthorizedException;

public class InvalidCredentialsException extends UnauthorizedException {

    public InvalidCredentialsException(Throwable t) {
        super("Invalid application credentials", t);
    }
}
