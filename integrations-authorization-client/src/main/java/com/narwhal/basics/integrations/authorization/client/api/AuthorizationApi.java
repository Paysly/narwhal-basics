package com.narwhal.basics.integrations.authorization.client.api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.common.base.Joiner;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.exceptions.api.client.HttpClientException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.dto.SuccessTokenDTO;
import com.narwhal.basics.integrations.authorization.client.dto.Token;
import com.narwhal.basics.integrations.authorization.client.dto.TokenValidation;
import com.narwhal.basics.integrations.authorization.client.exceptions.InvalidCredentialsException;
import com.narwhal.basics.integrations.authorization.client.exceptions.InvalidTokenException;
import com.narwhal.basics.integrations.authorization.client.exceptions.InvalidTokenHasExpiredException;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;

@Singleton
public class AuthorizationApi {

    @Inject
    protected ApiFetchService apiFetchService;
    @Inject
    protected MicroservicesContext context;

    public SuccessTokenDTO validateToken(String jwtToken) {
        ApiPreconditions.checkNotNull(jwtToken, "jwtToken");
        //
        List<HTTPHeader> headerList = new ArrayList<>();
        headerList.add(new HTTPHeader("Content-type", MediaType.APPLICATION_JSON));
        //
        try {
            SuccessTokenDTO successTokenDTO = apiFetchService.fetch(context.getAuthorizationEndpoint(), HTTPMethod.POST, headerList,
                    new TokenValidation(jwtToken), SuccessTokenDTO.class);
            return successTokenDTO;
        } catch (HttpClientException e) {
            if (e.getCode() == InvalidTokenHasExpiredException.HTTP_ERROR_CODE) {
                throw new InvalidTokenHasExpiredException(e);
            } else {
                throw new InvalidTokenException(e);
            }
        } catch (Exception e) {
            throw new InvalidTokenException(e);
        }
    }

    public Token authorize(String clientId, Set<ApplicationScopeTypes> scopes) {
        ApiPreconditions.checkNotNull(scopes, "scopes");
        //
        String clientSecret = context.getClientIdSecret().get(clientId);
        ApiPreconditions.checkNotNull(clientSecret, "clientSecretScopes");
        //
        List<HTTPHeader> headerList = new ArrayList<>();
        headerList.add(new HTTPHeader("Content-type", MediaType.APPLICATION_JSON));
        //
        Map<String, String> params = new HashMap<>();
        params.put("clientKey", clientId);
        params.put("clientSecret", clientSecret);
        //
        try {
            params.put("scopes", URLEncoder.encode(Joiner.on("|").join(scopes), "UTF-8"));
            Token token = apiFetchService.fetch(context.getAuthorizationEndpoint(), HTTPMethod.GET, headerList, params, Token.class);
            return token;
        } catch (Exception e) {
            throw new InvalidCredentialsException(e);
        }
    }
}
