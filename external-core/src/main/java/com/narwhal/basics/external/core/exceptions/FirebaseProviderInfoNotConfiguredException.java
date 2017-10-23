package com.narwhal.basics.external.core.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class FirebaseProviderInfoNotConfiguredException extends ServiceUnavailableException {

    public static final String MESSAGE = "Firebase provider info not configured yet. Missing info: ";

    public FirebaseProviderInfoNotConfiguredException(String field) {
        super(MESSAGE + field);
    }
}
