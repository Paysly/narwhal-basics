package com.narwhal.basics.integrations.authorization.client.exceptions;

import com.narwhal.basics.core.rest.exceptions.api.UnauthorizedException;

public class InvalidTokenHasExpiredException extends UnauthorizedException {

    public static final int HTTP_ERROR_CODE = 4011;

    public InvalidTokenHasExpiredException(Throwable t) {
        super("Token has expired. Ask for a new one", t);
        code = HTTP_ERROR_CODE;
    }
}
