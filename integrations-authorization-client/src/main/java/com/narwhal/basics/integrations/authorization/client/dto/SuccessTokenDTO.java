package com.narwhal.basics.integrations.authorization.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;

import java.io.Serializable;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessTokenDTO implements Serializable {
    private String id;
    private long expiration;
    private Set<ApplicationScopeTypes> scopes;
    private ApplicationEnvironmentTypes environment;

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

    public ApplicationEnvironmentTypes getEnvironment() {
        return environment;
    }

    public void setEnvironment(ApplicationEnvironmentTypes environment) {
        this.environment = environment;
    }
}
