package com.narwhal.basics.integrations.authorization.client.dto;

import java.io.Serializable;

public class TokenValidation implements Serializable {
    private String jwt;

    public TokenValidation() {
    }

    public TokenValidation(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
