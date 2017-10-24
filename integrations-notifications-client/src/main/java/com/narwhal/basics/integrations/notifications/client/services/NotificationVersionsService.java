package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationVersionDTO;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationVersionsDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationVersionsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Singleton
public class NotificationVersionsService {
    @Inject
    private NotificationVersionsEndpoint notificationVersionsApi;
    @Inject
    private MemcachedService memcachedService;

    public List<NotificationVersionDTO> getVersions() {
        //
        NotificationVersionsDTO versions = (NotificationVersionsDTO) memcachedService.get(MemcachedConstants.NOTIFICATION_VERSION_KEYS);
        //
        if (versions == null) {
            versions = new NotificationVersionsDTO(notificationVersionsApi.getVersions());
            memcachedService.put(MemcachedConstants.NOTIFICATION_VERSION_KEYS, versions);
        }
        return versions.getVersions();
    }

    public NotificationVersionDTO createVersion(NotificationVersionDTO version) {
        ApiPreconditions.checkNotNull(version, "version");
        //
        version = notificationVersionsApi.createVersion(version);
        memcachedService.delete(MemcachedConstants.NOTIFICATION_VERSION_KEYS);
        //
        if (StringUtils.isNotEmpty(version.getVersionIdToCopy())) {
            dropAllCaches(version.getId());
        } else {
            String groupKey = String.format(MemcachedConstants.NOTIFICATION_GROUPS_KEYS, version.getId());
            memcachedService.delete(groupKey);
        }
        //
        return version;
    }

    public NotificationVersionDTO updateVersion(NotificationVersionDTO version) {
        ApiPreconditions.checkNotNull(version, "version");
        //
        version = notificationVersionsApi.updateVersion(version);
        memcachedService.delete(MemcachedConstants.NOTIFICATION_VERSION_KEYS);
        //
        return version;
    }

    public void deleteVersion(String versionId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        //
        notificationVersionsApi.deleteVersion(versionId);
        //
        // Delete all from cache
        //
        dropAllCaches(versionId);
    }

    private void dropAllCaches(String versionId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        // versions
        memcachedService.delete(MemcachedConstants.NOTIFICATION_VERSION_KEYS);
        //
        String groupKey = String.format(MemcachedConstants.NOTIFICATION_GROUPS_KEYS, versionId);
        ArrayList<LinkedHashMap> groups = (ArrayList<LinkedHashMap>) memcachedService.get(groupKey);
        if (groups != null) {
            for (LinkedHashMap<String, Object> groupDTO : groups) {
                String groupNotificationKey = String.valueOf(groupDTO.get("notificationKey"));
                String notificationKey = String.format(MemcachedConstants.NOTIFICATION_ITEMS_KEYS, versionId, groupNotificationKey);
                ArrayList<LinkedHashMap> items = (ArrayList<LinkedHashMap>) memcachedService.get(groupNotificationKey);
                //
                if (items != null) {
                    for (LinkedHashMap<String, Object> itemDTO : items) {
                        String key = String.format(MemcachedConstants.NOTIFICATION_TEMPLATES_KEYS, versionId, groupNotificationKey, itemDTO.get("notificationKey"));
                        memcachedService.delete(key);
                    }
                }
                memcachedService.delete(notificationKey);
            }
            memcachedService.delete(groupKey);
        }
    }
}
