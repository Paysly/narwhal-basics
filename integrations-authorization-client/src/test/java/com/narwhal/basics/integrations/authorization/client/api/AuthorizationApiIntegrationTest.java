package com.narwhal.basics.integrations.authorization.client.api;

import org.junit.Test;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integration.authorization.api.support.BaseEndpointIntegrationTest;
import com.narwhal.basics.integrations.authorization.client.exceptions.InvalidTokenHasExpiredException;

/**
 * @author Tomas de Priede
 */
public class AuthorizationApiIntegrationTest extends BaseEndpointIntegrationTest {

    @Override
    protected void initEndpoint() {
        authorizationApi.apiFetchService = apiFetchService;
        authorizationApi.context = new MicroservicesContext() {

            @Override
            public String getStagingBaseServerUrl() {
                return "";
            }

            @Override
            public String getProductionBaseServerUrl() {
                return "";
            }
        };
        authorizationApi.context.setAuthorizationEndpoint(getAuthorizationEndpoint());
    }

    @Override
    protected String getAuthorizationEndpoint() {
        return "https://positano-authorization.appspot.com/api/v1/authorization/";
    }

    @Test(expected = InvalidTokenHasExpiredException.class)
    public void validateToken_expired() {
        String jwtToken = "eyJraWQiOiJyYWNlLWNhcGl0YWwtYWRtaW4tZGV2ZWxvcG1lbnQiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbnZpcm9ubWVudCI6ImRldmVsb3BtZW50IiwiaXNzIjoiTmFyd2hhbCBCYXNpY3MiLCJzY29wZXMiOiJTRU5EX05PVElGSUNBVElPTnt9Tk9USUZJQ0FUSU9OX0FQUExJQ0FUSU9OX1NFVFRJTkdTe31OT1RJRklDQVRJT05fVEVNUExBVEVTe31OT1RJRklDQVRJT05fVVNFUl9TRVRUSU5HU3t9Tk9USUZJQ0FUSU9OX1VTRVJTe31OT1RJRklDQVRJT05fTE9HU3t9Tk9USUZJQ0FUSU9OX0NPVU5URVJTIiwiZXhwIjoxNTEzOTgzMzgxLCJpYXQiOjE1MTEzOTEzODF9.u4Lfp6O1GxQ4HGGp9_2pCCZl0i_-K7CBJvwgsCixp9U";
        //
        authorizationApi.validateToken(jwtToken);
    }
}
