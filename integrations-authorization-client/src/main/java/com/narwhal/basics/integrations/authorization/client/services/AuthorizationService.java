package com.narwhal.basics.integrations.authorization.client.services;

import com.google.appengine.api.memcache.Expiration;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.authorization.client.api.AuthorizationApi;
import com.narwhal.basics.integrations.authorization.client.dao.ApplicationTokenDao;
import com.narwhal.basics.integrations.authorization.client.dto.SuccessTokenDTO;
import com.narwhal.basics.integrations.authorization.client.dto.Token;
import com.narwhal.basics.integrations.authorization.client.exceptions.InvalidScopeException;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Singleton
public class AuthorizationService {
    @Inject
    private AuthorizationApi authorizationApi;
    @Inject
    private MemcachedService memcachedService;
    @Inject
    private ApplicationTokenDao applicationTokenDao;

    public ApplicationToken getApplicationToken(String clientId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        Set<ApplicationScopeTypes> allScopes = Sets.newTreeSet(Arrays.asList(ApplicationScopeTypes.values()));
        String applicationTokenId = Joiner.on("|").join(allScopes);
        //
        ApplicationToken applicationToken = (ApplicationToken) memcachedService.get(applicationTokenId);
        if (applicationToken == null) {
            try {
                applicationToken = applicationTokenDao.get(ApplicationToken.class, applicationTokenId);
                memcachedService.put(applicationTokenId, applicationToken, Expiration.onDate(applicationToken.getExpirationDate()));
            } catch (EntityNotFoundException e) {
                //Token not found in memcached neither in db. Probably a change of scopes or first run
                applicationToken = renewApplicationToken(clientId);
            }
        }
        //
        return applicationToken;
    }

    public ApplicationToken renewApplicationToken(String clientId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        Set<ApplicationScopeTypes> allScopes = Sets.newTreeSet(Arrays.asList(ApplicationScopeTypes.values()));
        String applicationTokenId = Joiner.on("|").join(allScopes);
        Token token = authorizationApi.authorize(clientId, allScopes);
        ApplicationToken applicationToken = new ApplicationToken();
        applicationToken.init(applicationTokenId, token.getJwt(), token.getExpiration());
        // Save in db
        applicationTokenDao.save(applicationToken);
        // Put in memcached
        memcachedService.put(applicationTokenId, applicationToken, Expiration.onDate(applicationToken.getExpirationDate()));
        //
        return applicationToken;
    }

    public SuccessTokenDTO validateToken(String jwtToken, ApplicationScopeTypes requiredScope) {
        ApiPreconditions.checkNotNull(jwtToken, "jwtToken");
        ApiPreconditions.checkNotNull(requiredScope, "requiredScope");
        //
        SuccessTokenDTO successTokenDTO = (SuccessTokenDTO) memcachedService.get(jwtToken);
        //
        if (successTokenDTO == null) {
            successTokenDTO = authorizationApi.validateToken(jwtToken);
            memcachedService.put(jwtToken, successTokenDTO, Expiration.onDate(new Date(successTokenDTO.getExpiration())));
        }
        //
        if (!successTokenDTO.getScopes().contains(requiredScope)) {
            throw new InvalidScopeException(requiredScope.toString());
        }
        return successTokenDTO;
    }
}
