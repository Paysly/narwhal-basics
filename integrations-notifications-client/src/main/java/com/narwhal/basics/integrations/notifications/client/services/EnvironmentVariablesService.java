package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.settings.EnvironmentVariableDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.EnvironmentVariablesEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

import java.util.ArrayList;

@Singleton
public class EnvironmentVariablesService {

    @Inject
    private EnvironmentVariablesEndpoint environmentVariablesApi;
    @Inject
    private MemcachedService memcachedService;

    public ArrayList<EnvironmentVariableDTO> getEnvironmentVariables(String clientId) {
        ArrayList<EnvironmentVariableDTO> variables = (ArrayList) memcachedService.get(MemcachedConstants.ENVIRONMENT_VARIABLES_KEY);
        if (variables == null) {
            variables = new ArrayList<>(environmentVariablesApi.getEnvironmentVariables(clientId));
            memcachedService.put(MemcachedConstants.ENVIRONMENT_VARIABLES_KEY, variables);
        }
        //
        return variables;
    }

    public void updateEnvironmentVariables(String clientId, ArrayList<EnvironmentVariableDTO> variable) {
        ApiPreconditions.checkNotNull(variable, "variable");
        //
        memcachedService.delete(MemcachedConstants.ENVIRONMENT_VARIABLES_KEY);
        environmentVariablesApi.setEnvironmentVariables(clientId, variable);
        memcachedService.put(MemcachedConstants.ENVIRONMENT_VARIABLES_KEY, variable);
    }
}
