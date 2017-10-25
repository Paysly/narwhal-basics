package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationGroupDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationGroupsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

import java.util.ArrayList;

@Singleton
public class NotificationGroupsService {
    @Inject
    private NotificationGroupsEndpoint notificationGroupsApi;
    @Inject
    private MemcachedService memcachedService;

    public ArrayList<NotificationGroupDTO> getGroups(String clientId, String versionId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_GROUPS_KEYS, versionId);
        ArrayList<NotificationGroupDTO> groups = (ArrayList<NotificationGroupDTO>) memcachedService.get(key);
        //
        if (groups == null) {
            groups = notificationGroupsApi.getGroups(clientId, versionId);
            memcachedService.put(key, groups);
        }
        return groups;
    }


    public ArrayList<NotificationGroupDTO> updateGroups(String clientId, String versionId, ArrayList<NotificationGroupDTO> groups) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groups, "groups");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_GROUPS_KEYS, versionId);
        groups = notificationGroupsApi.updateGroups(clientId, versionId, groups);
        memcachedService.put(key, groups);
        //
        return groups;
    }
}
