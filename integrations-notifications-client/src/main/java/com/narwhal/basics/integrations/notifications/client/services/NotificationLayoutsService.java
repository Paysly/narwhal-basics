package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationLayoutDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationLayoutsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

import java.util.ArrayList;

@Singleton
public class NotificationLayoutsService {
    @Inject
    private NotificationLayoutsEndpoint notificationLayoutsApi;
    @Inject
    private MemcachedService memcachedService;

    private String getMemcachedKey(String versionId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_LAYOUTS_KEYS, versionId);
        return key;
    }

    private String getMemcachedKey(String versionId, String layoutId) {
        String key = getMemcachedKey(versionId);
        key = String.format(key + "_%s", layoutId);
        return key;
    }

    public ArrayList<NotificationLayoutDTO> getLayouts(String versionId) {
        String keys = getMemcachedKey(versionId);
        ArrayList<NotificationLayoutDTO> layouts = (ArrayList) memcachedService.get(keys);
        //
        if (layouts == null) {
            layouts = notificationLayoutsApi.getLayouts(versionId);
            memcachedService.put(keys, layouts);
        }
        return layouts;
    }

    public NotificationLayoutDTO getLayout(String versionId, String layoutId) {
        String key = getMemcachedKey(versionId, layoutId);
        NotificationLayoutDTO layouts = (NotificationLayoutDTO) memcachedService.get(key);
        //
        if (layouts == null) {
            layouts = notificationLayoutsApi.getLayout(versionId, layoutId);
            memcachedService.put(key, layouts);
        }
        return layouts;
    }


    public NotificationLayoutDTO createLayout(String versionId, NotificationLayoutDTO layout) {
        String keys = getMemcachedKey(versionId);
        String key = getMemcachedKey(versionId, layout.getId());
        //
        layout = notificationLayoutsApi.createLayout(versionId, layout);
        //
        ArrayList<NotificationLayoutDTO> layouts = (ArrayList) memcachedService.get(keys);
        //
        if (layouts != null) {
            //cache version found. Add new one to it.
            layouts.add(layout);
            memcachedService.put(keys, layouts);
        }
        //Add new one to unique cache
        memcachedService.put(key, layout);
        //
        return layout;
    }

    public NotificationLayoutDTO updateLayout(String versionId, NotificationLayoutDTO layout) {
        String keys = getMemcachedKey(versionId);
        String key = getMemcachedKey(versionId, layout.getId());
        //
        layout = notificationLayoutsApi.updateLayout(versionId, layout);
        //
        memcachedService.delete(keys);
        //Add new one to unique cache
        memcachedService.put(key, layout);
        //
        return layout;
    }

    public void deleteLayout(String versionId, String layoutId) {
        String keys = getMemcachedKey(versionId);
        String key = getMemcachedKey(versionId, layoutId);
        //
        notificationLayoutsApi.deleteLayout(versionId, layoutId);
        //
        //Remove from unique cache
        memcachedService.delete(keys);
        memcachedService.delete(key);
    }
}
