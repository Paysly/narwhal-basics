package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.settings.EnvironmentVariableDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.EnvironmentVariablesUnavailable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class EnvironmentVariablesEndpoint extends BaseNarwhalApi {

    private String ENVIRONMENT_VARIABLES_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public EnvironmentVariablesEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        ENVIRONMENT_VARIABLES_URL = microservicesContext.getNotificationsEndpoint() + "/application/environment/";
    }

    public List<EnvironmentVariableDTO> getEnvironmentVariables(String clientId) {
        try {
            return Arrays.asList(securedGet(clientId, ENVIRONMENT_VARIABLES_URL, EnvironmentVariableDTO[].class));
        } catch (Exception e) {
            throw new EnvironmentVariablesUnavailable("Failed to fetch environment variables", e);
        }
    }

    public void setEnvironmentVariables(String clientId, ArrayList<EnvironmentVariableDTO> variables) {
        ApiPreconditions.checkNotNull(variables, "variables");
        try {
            securedPost(clientId, ENVIRONMENT_VARIABLES_URL, variables, null);
        } catch (Exception e) {
            throw new EnvironmentVariablesUnavailable("Failed to update environment variables", e);
        }
    }
}
