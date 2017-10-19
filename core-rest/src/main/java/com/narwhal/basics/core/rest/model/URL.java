package com.narwhal.basics.core.rest.model;

/**
 * @author Tomas de Priede
 */
public class URL {

    private String path;

    public URL(String path) {
        this.path = path;
    }

    public static URL forPath(String path) {
        return new URL(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
