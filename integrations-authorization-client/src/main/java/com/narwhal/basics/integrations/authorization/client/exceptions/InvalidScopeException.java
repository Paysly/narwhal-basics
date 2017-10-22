package com.narwhal.basics.integrations.authorization.client.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.UnauthorizedException;

public class InvalidScopeException extends UnauthorizedException {
    public InvalidScopeException(String requiredScope) {
        super("Token has no grants for scope: " + requiredScope);
    }
}
