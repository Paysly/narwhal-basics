package com.narwhal.basics.core.rest.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Created by tomyair on 7/13/17.
 */
public class GoogleHeadersUtils {

    public static String getClientIp(String xForwardedFor) {
        return StringUtils.split(xForwardedFor, ",")[0];
    }
}
