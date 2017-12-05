package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.notifications.client.dto.messages.NotificationForceMessageDTO;
import com.narwhal.basics.integrations.notifications.client.dto.messages.NotificationMessageDTO;
import com.narwhal.basics.integrations.notifications.client.dto.messages.NotificationMessageResponseDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.MessageNotificationUnavailable;

@Singleton
public class NotificationMessageEndpoint extends BaseNarwhalApi {

    private final String NOTIFICATION_MESSAGE_URL;
    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationMessageEndpoint(MicroservicesContext microservicesContext, ApiFetchService apiFetchService, AuthorizationService authorizationService) {
        this.microservicesContext = microservicesContext;
        this.apiFetchService = apiFetchService;
        this.authorizationService = authorizationService;
        //
        NOTIFICATION_MESSAGE_URL = microservicesContext.getNotificationsEndpoint() + "/message/";
    }

    public NotificationMessageResponseDTO sendMessageNotification(String clientId, NotificationMessageDTO notificationMessageDTO) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        try {
            String url = NOTIFICATION_MESSAGE_URL;
            return securedPost(clientId, url, notificationMessageDTO, NotificationMessageResponseDTO.class);
        } catch (Exception e) {
            throw new MessageNotificationUnavailable("Failed to fetch Notification Force Message", e);
        }
    }

    public NotificationMessageResponseDTO sendForceMessageNotification(String clientId, NotificationForceMessageDTO notificationForceMessageDTO) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        try {
            String url = NOTIFICATION_MESSAGE_URL + "force";
            return securedPost(clientId, url, notificationForceMessageDTO, NotificationMessageResponseDTO.class);
        } catch (Exception e) {
            throw new MessageNotificationUnavailable("Failed to fetch Notification Force Message", e);
        }
    }
}
