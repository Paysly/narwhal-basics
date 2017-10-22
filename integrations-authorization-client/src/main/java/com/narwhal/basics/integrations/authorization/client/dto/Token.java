package com.narwhal.basics.integrations.authorization.client.dto;

import java.io.Serializable;

public class Token implements Serializable {
    private String jwt;
    private long expiration;

    public Token() {
    }

    public Token(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
