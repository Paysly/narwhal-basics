package com.narwhal.basics.external.core.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.dto.EnvironmentVariableDTO;
import com.narwhal.basics.external.core.model.EnvironmentVariable;
import com.narwhal.basics.external.core.services.EnvironmentVariablesCachedService;
import com.narwhal.basics.integrations.authorization.client.dto.SuccessTokenDTO;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Singleton
@Path("/v1/application/environment/")
public class EnvironmentVariablesApi {


    @Inject
    private EnvironmentVariablesCachedService cachedService;
    @Inject
    private AuthorizationService authorizationService;

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnvironmentVariables(@HeaderParam("Auth") String authHeader) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        //
        ArrayList<EnvironmentVariable> variables = cachedService.getCachedEnvironmentVariables(tokenDTO.getEnvironment());
        //
        return Response.ok(variables).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEnvironmentVariables(@HeaderParam("Auth") String authHeader,
                                               ArrayList<EnvironmentVariableDTO> variables) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        //
        cachedService.updateCachedEnvironmentVariables(tokenDTO.getEnvironment(), variables);
        //
        return Response.ok().build();
    }
}
