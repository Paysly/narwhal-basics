package com.narwhal.basics.core.rest.utils;

/**
 * @author Tomas de Priede
 */
public class ThreadUtils {

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
