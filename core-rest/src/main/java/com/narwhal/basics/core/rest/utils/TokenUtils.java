package com.narwhal.basics.core.rest.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;

/**
 * @author Tomas de Priede
 */
public class TokenUtils {

    public static String getTokenFromAuthenticationHeader(String authorizationHeader) {
        Preconditions.checkNotNull(authorizationHeader);
        return StringUtils.remove(authorizationHeader, "Basic ");
    }
}
