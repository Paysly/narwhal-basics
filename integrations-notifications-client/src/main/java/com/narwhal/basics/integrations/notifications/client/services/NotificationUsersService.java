package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.narwhal.basics.core.rest.model.paging.PagingResult;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationUserDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationUserEndpoint;
import org.apache.commons.lang.StringUtils;


public class NotificationUsersService {

    @Inject
    private NotificationUserEndpoint notificationUserEndpoint;

    public PagingResult<NotificationUserDTO> getUsers(String clientId, int size, String cursor, String email) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        PagingResult<NotificationUserDTO> pagingResult = notificationUserEndpoint.getUsers(clientId, size, cursor, email);
        return pagingResult;
    }

    public NotificationUserDTO getUser(String clientId, String userId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        ApiPreconditions.checkNotNull(userId, "userId");
        NotificationUserDTO notificationUserDTO = notificationUserEndpoint.getUser(clientId, userId);
        return notificationUserDTO;
    }

    public NotificationUserDTO saveUser(String clientId, NotificationUserDTO notificationUserDTO) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        ApiPreconditions.checkNotNull(notificationUserDTO, "notificationUserDTO");
        return notificationUserEndpoint.saveUser(clientId, notificationUserDTO);
    }

    public void patchUser(String clientId, String userId, NotificationUserDTO notificationUserDTO) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        ApiPreconditions.checkNotNull(userId, "userId");
        ApiPreconditions.checkNotNull(notificationUserDTO, "notificationUserDTO");
        //
        NotificationUserDTO currentNotificationUserDTO = notificationUserEndpoint.getUser(clientId, userId);
        //
        if (StringUtils.equalsIgnoreCase(notificationUserDTO.getEmail(), currentNotificationUserDTO.getEmail())) {
            notificationUserDTO.setEmail(null);
        }
        //
        if (StringUtils.equalsIgnoreCase(notificationUserDTO.getPhone(), currentNotificationUserDTO.getPhone())) {
            notificationUserDTO.setPhone(null);
        }
        //
        if (StringUtils.equals(notificationUserDTO.getFirebaseToken(), currentNotificationUserDTO.getFirebaseToken())) {
            notificationUserDTO.setFirebaseToken(null);
        }
        //
        notificationUserEndpoint.patchUser(clientId, userId, notificationUserDTO);
    }

    public void deleteUser(String clientId, String userId) {
        ApiPreconditions.checkNotNull(clientId, "clientId");
        ApiPreconditions.checkNotNull(userId, "userId");
        notificationUserEndpoint.deleteUser(clientId, userId);
    }
}
