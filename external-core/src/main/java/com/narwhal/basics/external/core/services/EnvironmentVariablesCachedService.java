package com.narwhal.basics.external.core.services;

import com.google.appengine.api.NamespaceManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.external.core.model.EnvironmentVariable;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;

import java.util.ArrayList;

@Singleton
public class EnvironmentVariablesCachedService {

    @Inject
    private EnvironmentVariableEditService editService;
    @Inject
    private MemcachedService memcachedService;
    @Inject
    private MicroservicesContext microservicesContext;

    public ArrayList<EnvironmentVariable> getCachedEnvironmentVariables(ApplicationEnvironmentTypes environment) {
        ApiPreconditions.checkNotNull(environment, "environment");
        String namespaceId = environment.toString();
        //
        NamespaceManager.set(namespaceId);
        ArrayList<EnvironmentVariable> variables = (ArrayList<EnvironmentVariable>) memcachedService.getFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId());
        if (variables == null) {
            variables = new ArrayList<>(editService.getUpdatables(null));
            memcachedService.putFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId(), variables);
        }
        NamespaceManager.set(null);
        return variables;
    }

    public void updateCachedEnvironmentVariables(ApplicationEnvironmentTypes environment, ArrayList<EnvironmentVariable> variables) {
        ApiPreconditions.checkNotNull(environment, "environment");
        String namespaceId = environment.toString();
        //
        NamespaceManager.set(namespaceId);
        variables = new ArrayList<>(editService.editModels(null, variables));
        memcachedService.putFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId(), variables);
        NamespaceManager.set(null);
    }
}
