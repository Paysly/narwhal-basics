package com.narwhal.basics.integrations.authorization.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessTokenDTO implements Serializable {
    private String id;
    private long expiration;
    private Set<ApplicationScopeTypes> scopes;
    private ApplicationEnvironmentTypes environment;
}
