package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationItemDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationItemsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class NotificationItemsService {
    @Inject
    private NotificationItemsEndpoint notificationItemsApi;
    @Inject
    private MemcachedService memcachedService;

    public ArrayList<NotificationItemDTO> getItems(String clientId, String versionId, String groupKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_ITEMS_KEYS, versionId, groupKey);
        ArrayList<NotificationItemDTO> groups = (ArrayList<NotificationItemDTO>) memcachedService.get(key);
        //
        if (groups == null) {
            groups = notificationItemsApi.getItems(clientId, versionId, groupKey);
            memcachedService.put(key, groups);
        }
        return groups;
    }


    public List<NotificationItemDTO> updateItems(String clientId, String versionId, String groupKey, List<NotificationItemDTO> items) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(items, "items");
        //
        items = notificationItemsApi.updateItems(clientId, versionId, groupKey, items);
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_GROUPS_KEYS, versionId);
        memcachedService.delete(key);
        //
        List<NotificationItemDTO> itemsFiltered = new ArrayList<>();
        for (NotificationItemDTO item : items) {
            if (StringUtils.equals(item.getGroupNotificationKey(), groupKey)) {
                // Group key was update, remove them because it was moved
                itemsFiltered.add(item);
            }
            key = String.format(MemcachedConstants.NOTIFICATION_ITEMS_KEYS, versionId, groupKey);
            memcachedService.delete(key);
        }
        //
        return itemsFiltered;
    }
}
