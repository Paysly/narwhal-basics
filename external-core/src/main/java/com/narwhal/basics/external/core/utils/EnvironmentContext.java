package com.narwhal.basics.external.core.utils;

/**
 * @author Tomas de Priede
 */
public class EnvironmentContext {

    public EnvironmentContextUrl urls = new EnvironmentContextUrl();

    public EnvironmentContext() {
    }

    public EnvironmentContext(String url) {
        this.urls = new EnvironmentContextUrl(url);
    }

    public EnvironmentContextUrl getUrls() {
        return urls;
    }

    public void setUrls(EnvironmentContextUrl urls) {
        this.urls = urls;
    }
}
