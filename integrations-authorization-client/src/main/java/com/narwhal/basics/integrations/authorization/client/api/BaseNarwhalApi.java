package com.narwhal.basics.integrations.authorization.client.api;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseNarwhalApi {

    @Inject
    protected ApiFetchService apiFetchService;
    @Inject
    protected AuthorizationService authorizationService;

    protected List<HTTPHeader> prepareHeaders(String clientId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        ApplicationToken applicationToken = authorizationService.getApplicationToken(clientId);
        //
        List<HTTPHeader> headerList = new ArrayList<>();
        headerList.add(new HTTPHeader("Content-type", MediaType.APPLICATION_JSON));
        headerList.add(new HTTPHeader("Auth", applicationToken.getToken()));
        //
        return headerList;
    }

    protected <T> T securedGet(String clientId, String url, Class<T> responseClazz) {
        Map<String, String> params = new HashMap<>();
        return securedGet(clientId, url, params, responseClazz);
    }

    protected <T> T securedGet(String clientId, String url, Map<String, String> params, Class<T> responseClazz) {
        return apiFetchService.fetch(url, HTTPMethod.GET, prepareHeaders(clientId), params, responseClazz);
    }

    protected <T> T securedPost(String clientId, String url, Class<T> responseClazz) {
        return securedPost(clientId, url, null, responseClazz);
    }

    protected <T> T securedPost(String clientId, String url, Serializable params) {
        return securedPost(clientId, url, params, null);
    }

    protected <T> T securedPost(String clientId, String url, Serializable params, Class<T> responseClazz) {
        return apiFetchService.fetch(url, HTTPMethod.POST, prepareHeaders(clientId), params, responseClazz);
    }

    protected <T> T securedPut(String clientId, String url, Class<T> responseClazz) {
        return securedPut(clientId, url, null, responseClazz);
    }

    protected <T> T securedPut(String clientId, String url, Serializable params) {
        return securedPut(clientId, url, params, null);
    }

    protected <T> T securedPut(String clientId, String url, Serializable params, Class<T> responseClazz) {
        return apiFetchService.fetch(url, HTTPMethod.PUT, prepareHeaders(clientId), params, responseClazz);
    }

    protected <T> T securedDelete(String clientId, String url) {
        return securedDelete(clientId, url, null);
    }

    protected <T> T securedDelete(String clientId, String url, Map<String, String> params) {
        return apiFetchService.fetch(url, HTTPMethod.DELETE, prepareHeaders(clientId), params, null);
    }
}
