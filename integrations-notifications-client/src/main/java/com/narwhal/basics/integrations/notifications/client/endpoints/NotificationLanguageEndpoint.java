package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;

@Singleton
public class NotificationLanguageEndpoint extends BaseNarwhalApi {
    private String NOTIFICATION_LANGUAGE_URL;
    private MicroservicesContext microservicesContext;
    //

    @Inject
    public NotificationLanguageEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        this.NOTIFICATION_LANGUAGE_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/languages/%s";
    }

    public String getTemplatesAsJson(String clientId, String name, String version) {
        try {
            String url = (String.format(this.NOTIFICATION_LANGUAGE_URL, version, name)) + "/json";
            return this.securedGet(clientId,url, String.class);
        } catch (ApiException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification languages", var4);
        }
    }
}
