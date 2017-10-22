package com.narwhal.basics.integrations.authorization.client.dto;


import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;

import java.io.Serializable;
import java.util.Set;

public class SuccessTokenDTO implements Serializable {
    private String id;
    private long expiration;
    private Set<ApplicationScopeTypes> scopes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public Set<ApplicationScopeTypes> getScopes() {
        return scopes;
    }

    public void setScopes(Set<ApplicationScopeTypes> scopes) {
        this.scopes = scopes;
    }
}
