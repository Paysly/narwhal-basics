package com.narwhal.basics.integrations.authorization.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {
    private String jwt;
    private long expiration;

    public Token(String jwt) {
        this.jwt = jwt;
    }
}
