package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.model.paging.PagingResult;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.notifications.client.dto.users.NotificationUserDTO;
import com.narwhal.basics.integrations.notifications.client.dto.users.NotificationUserPagingResultDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.UserNotificationUnavailable;

import java.util.HashMap;
import java.util.List;


@Singleton
public class NotificationUserEndpoint extends BaseNarwhalApi {

    private final String NOTIFICATION_URL;
    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationUserEndpoint(MicroservicesContext microservicesContext, ApiFetchService apiFetchService, AuthorizationService authorizationService) {
        this.microservicesContext = microservicesContext;
        this.apiFetchService = apiFetchService;
        this.authorizationService = authorizationService;
        //
        NOTIFICATION_URL = "https://positano-notifications.appspot.com/api/v1" + "/users/";
    }

    public NotificationUserPagingResultDTO getUsers(String clientId, Integer size, String cursor, String email) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            //
            if (email != null) {
                hashMap.put("email", email);
            }
            if (size != null) {
                hashMap.put("size", size.toString());
            }
            if (cursor != null) {
                hashMap.put("cursor", cursor);
            }
            //
            return securedGet(clientId, NOTIFICATION_URL, hashMap, NotificationUserPagingResultDTO.class);
        } catch (Exception e) {
            throw new UserNotificationUnavailable("Failed to fetch Notification NotificationUserDTO", e);
        }
    }

    public NotificationUserDTO getUser(String clientId, String userId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        String url = NOTIFICATION_URL + userId;
        try {
            return securedGet(clientId, url, NotificationUserDTO.class);
        } catch (Exception e) {
            throw new UserNotificationUnavailable("Failed to fetch Notification NotificationUserDTO", e);
        }
    }

    public NotificationUserDTO saveUser(String clientId, NotificationUserDTO notificationUserDTO) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        try {
            return securedPost(clientId, NOTIFICATION_URL, notificationUserDTO, NotificationUserDTO.class);
        } catch (Exception e) {
            throw new UserNotificationUnavailable("Failed to fetch Notification NotificationUserDTO", e);
        }
    }

    public NotificationUserDTO patchUser(String clientId, String userId, NotificationUserDTO notificationUserDTO) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        String url = NOTIFICATION_URL + userId;
        try {
            return securedPut(clientId, url, notificationUserDTO, NotificationUserDTO.class);
        } catch (Exception e) {
            throw new UserNotificationUnavailable("Failed to fetch Notification NotificationUserDTO", e);
        }
    }

    public NotificationUserDTO deleteUser(String clientId, String userId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        String url = NOTIFICATION_URL + userId;
        try {
            return securedDelete(clientId, url);
        } catch (Exception e) {
            throw new UserNotificationUnavailable("Failed to fetch Notification NotificationUserDTO", e);
        }
    }
}
