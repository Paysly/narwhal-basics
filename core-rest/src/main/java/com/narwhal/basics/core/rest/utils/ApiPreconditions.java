package com.narwhal.basics.core.rest.utils;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class ApiPreconditions {

    public static void checkNotNull(Object obj, String objectName) {
        if (obj == null) {
            throw new BadRequestException(objectName + " must not be null.");
        }
    }
}
