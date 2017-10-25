package com.narwhal.basics.core.rest.utils;

import javax.servlet.http.HttpSession;

/**
 * @author Tomas de Priede
 */
public abstract class ServerWebConstants {

    /**
     * Return the plain url from the microservice configured.
     *
     * @param microservicesContext
     * @return
     */
    public static String getAppUrl(MicroservicesContext microservicesContext) {
        return getServerValue(microservicesContext.getLocalBaseServerUrl(),
                microservicesContext.getStagingBaseServerUrl(),
                microservicesContext.getProductionBaseServerUrl());
    }

    public static String getServerValue(String localValue, String stagingValue, String productionValue) {
        String value;
        switch (SharedConstants.currentServer()) {
            case LOCAL:
                value = localValue;
                break;
            case STAGING:
                value = stagingValue;
                break;
            case PRODUCTION:
            default:
                value = productionValue;
                break;
        }
        return value;
    }

    public static <T> T getSessionAttribute(HttpSession session, String sessionAttribute) {
        Object object = session.getAttribute(sessionAttribute);
        if (object != null) {
            return (T) object;
        } else {
            return null;
        }
    }
}
