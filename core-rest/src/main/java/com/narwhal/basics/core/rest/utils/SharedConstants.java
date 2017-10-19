package com.narwhal.basics.core.rest.utils;

import com.google.appengine.api.utils.SystemProperty;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
public class SharedConstants {

    private static final Logger logger = Logger.getLogger(SharedConstants.class.getSimpleName());
    private static ResourceBundle appProperties;
    private static String environment;
    public static boolean junit = false;
    static {
        try {
            appProperties = ResourceBundle.getBundle("app");
            environment = appProperties.getString("application.environment");
            //
        } catch (MissingResourceException e) {
            logger.log(Level.WARNING, "No app.properties found. Using default configuration", e);
        }
    }

    //
    public static boolean isRunningOnLocalServer() {
        SystemProperty.Environment.Value server = SystemProperty.environment.value();
        return server == SystemProperty.Environment.Value.Development;
    }

    public static boolean isRunningOnAppEngine() {
        SystemProperty.Environment.Value server = SystemProperty.environment.value();
        return server == SystemProperty.Environment.Value.Production;
    }

    public static Server currentServer() {
        if (isRunningOnLocalServer()) {
            return Server.LOCAL;
        } else if (isRunningOnAppEngine()) {
            // Check diff between stating and prod
            if ("production".equalsIgnoreCase(environment)) {
                return Server.PRODUCTION;
            }
            return Server.STAGING;
        } else if (junit) {
            return Server.LOCAL;
        } else {
            throw new RuntimeException("Server not identified");
        }
    }

    public static ResourceBundle getAppProperties() {
        return appProperties;
    }

    public static String getCalendarMonthName(int month) {
        switch (month) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "N/A";
        }
    }
}
