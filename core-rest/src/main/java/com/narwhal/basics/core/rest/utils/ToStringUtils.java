package com.narwhal.basics.core.rest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Tomas de Priede
 */
public class ToStringUtils {

    public static String toString(Object object) {
        return toString(object, true);
    }

    public static String toString(Object object, boolean className) {
        String toString = "";
        if (className) {
            toString += object.getClass().getSimpleName();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            toString += mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
        }
        return toString;
    }
}