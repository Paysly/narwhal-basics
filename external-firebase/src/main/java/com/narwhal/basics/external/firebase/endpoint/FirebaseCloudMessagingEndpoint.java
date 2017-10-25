package com.narwhal.basics.external.firebase.endpoint;

import com.google.api.server.spi.config.Singleton;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.model.FirebaseSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.firebase.dto.FirebaseCloudMessage;
import com.narwhal.basics.external.firebase.dto.FirebaseCloudMessageResponse;
import com.narwhal.basics.external.firebase.dto.FirebasePayload;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomyair on 7/14/17.
 */
@Singleton
public class FirebaseCloudMessagingEndpoint {

    protected static final String AUTHORIZATION_HEADER = "Authorization";
    @Inject
    private ApplicationSettingsCachedService cachedService;
    @Inject
    private ApiFetchService apiFetchService;

    /**
     * Send Message
     *
     * @param payload
     * @return
     */
    public FirebaseCloudMessageResponse sendMessage(ApplicationEnvironmentTypes environment, String to, FirebasePayload payload) {
        ApiPreconditions.checkNotNull(environment, "environment");
        ApiPreconditions.checkNotNull(to, "to");
        ApiPreconditions.checkNotNull(payload, "payload");
        //
        FirebaseSettings firebaseSettings = cachedService.getCachedApplicationSettings(environment);
        firebaseSettings.checkFirebaseData();
        //
        List<HTTPHeader> headerList = new ArrayList<>();
        headerList.add(new HTTPHeader("Content-type", MediaType.APPLICATION_JSON));
        headerList.add(new HTTPHeader(AUTHORIZATION_HEADER, "key=" + firebaseSettings.getFirebaseServerKey()));
        //
        String url = firebaseSettings.getFirebaseMessagingUrl();
        //
        FirebaseCloudMessage cloudMessage = new FirebaseCloudMessage(to, payload.getNotification(), payload.getData());
        //
        FirebaseCloudMessageResponse response = apiFetchService.fetch(url, HTTPMethod.POST, headerList, cloudMessage, FirebaseCloudMessageResponse.class);
        //
        return response;
    }

    public void setCachedService(ApplicationSettingsCachedService cachedService) {
        this.cachedService = cachedService;
    }

    public void setApiFetchService(ApiFetchService apiFetchService) {
        this.apiFetchService = apiFetchService;
    }
}
