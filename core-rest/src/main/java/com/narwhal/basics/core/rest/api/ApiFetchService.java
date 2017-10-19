package com.narwhal.basics.core.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.urlfetch.*;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.ApplicationException;
import com.narwhal.basics.core.rest.exceptions.api.client.HttpClientException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.appengine.api.urlfetch.FetchOptions.Builder.allowTruncate;

/**
 * @author Tomas de Priede
 */
@Singleton
public class ApiFetchService {

    private final static Logger logger = Logger.getLogger(ApiFetchService.class.getSimpleName());
    private URLFetchService urlFetchService;

    public <T> T fetch(String url, HTTPMethod method, List<HTTPHeader> headers, Serializable params, Class<T> responseClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String body = mapper.writeValueAsString(params);
            return fetch(url, method, headers, body, responseClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T fetch(String url, HTTPMethod method, List<HTTPHeader> headers, Map<String, String> params, Class<T> responseClass) {
        List<String> paramAndValues = new ArrayList<>();
        if (params != null) {
            for (Map.Entry e : params.entrySet()) {
                paramAndValues.add(e.getKey() + "=" + e.getValue());
            }
        }
        String body = null;
        switch (method) {
            case GET:
            case DELETE:
                url += "?" + Joiner.on("&").join(paramAndValues);
                return fetch(url, method, headers, body, responseClass);
            default:
                body = Joiner.on("&").join(paramAndValues);
                return fetch(url, method, headers, body, responseClass);
        }
    }

    private <T> T fetch(String url, HTTPMethod method, List<HTTPHeader> headers, String body, Class<T> responseClass) {
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(method);
        //
        T jsonResponse = null;
        //
        try {
            logger.log(Level.INFO, "Fetching api url: " + url);
            //
            FetchOptions fetchOptions = allowTruncate().doNotFollowRedirects();
            //
            HTTPRequest request = new HTTPRequest(new URL(url), method, fetchOptions);
            request.getFetchOptions().setDeadline(30d);
            //
            if (headers != null) {
                for (HTTPHeader httpHeader : headers) {
                    request.setHeader(httpHeader);
                }
            }
            //
            //
            if (body != null) {
                request.setPayload(body.getBytes("UTF-8"));
            }
            //
            HTTPResponse response = urlFetchService.fetch(request);
            String stringResponse = new String(response.getContent(), "utf-8");
            logger.log(Level.FINE, "Body response: " + stringResponse);
            stringResponse = StringUtils.replace(StringUtils.replace(stringResponse, "\n", ""), "\t", "");
            //
            if (response.getResponseCode() >= 200 && response.getResponseCode() <= 250) {
                //
                if (responseClass != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    jsonResponse = mapper.readValue(stringResponse, responseClass);
                }
            } else {
                throw new HttpClientException(response.getResponseCode(), "Error on http client call: " + stringResponse, stringResponse);
            }
        } catch (IOException e) {
            throw new ApplicationException("Bad Gae App Request", e);
        }
        //
        return jsonResponse;
    }

    @Inject
    public void setUrlFetchService(URLFetchService urlFetchService) {
        this.urlFetchService = urlFetchService;
    }
}
