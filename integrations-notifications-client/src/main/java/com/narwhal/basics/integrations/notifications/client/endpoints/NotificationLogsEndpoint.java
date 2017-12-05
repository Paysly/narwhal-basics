package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.api.server.spi.config.Singleton;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.notifications.client.dto.logs.NotificationLogDTO;
import com.narwhal.basics.integrations.notifications.client.dto.logs.NotificationLogDetailDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationLogsSettingsUnavailable;

import java.util.ArrayList;
import java.util.HashMap;

@Singleton
public class NotificationLogsEndpoint extends BaseNarwhalApi {

    private final String NOTIFICATIONS_URL;
    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationLogsEndpoint(MicroservicesContext microservicesContext, ApiFetchService apiFetchService, AuthorizationService authorizationService) {
        this.microservicesContext = microservicesContext;
        this.apiFetchService = apiFetchService;
        this.authorizationService = authorizationService;
        //
        NOTIFICATIONS_URL = microservicesContext.getNotificationsEndpoint() + "/logs/";
    }

    public NotificationLogDetailDTO getNotificationLogDetail(String clientId, String notificationId) {
        try {
            String url = NOTIFICATIONS_URL + notificationId;
            //
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("notificationId", notificationId);
            return securedGet(clientId, url, hashMap, NotificationLogDetailDTO.class);
        } catch (Exception e) {
            throw new NotificationLogsSettingsUnavailable("Failed to fetch notification log", e);
        }
    }

    public ArrayList<NotificationLogDTO> getNotificationLogs(String clientId) {
        try {
            return securedGet(clientId, NOTIFICATIONS_URL, ArrayList.class);
        } catch (Exception e) {
            throw new NotificationLogsSettingsUnavailable("Failed to fetch notification log", e);
        }
    }

    public ArrayList<NotificationLogDTO> getNotificationLogsByUser(String clientId, String userTo) {
        try {
            ApiPreconditions.checkNotNull(userTo, "userTo");
            ApiPreconditions.checkNotNull(clientId, "clientId");
            //
            String url = NOTIFICATIONS_URL + "user/" + userTo;
            //
            return securedGet(clientId, url, ArrayList.class);
        } catch (Exception e) {
            throw new NotificationLogsSettingsUnavailable("Failed to fetch notification log by user", e);
        }
    }
}
