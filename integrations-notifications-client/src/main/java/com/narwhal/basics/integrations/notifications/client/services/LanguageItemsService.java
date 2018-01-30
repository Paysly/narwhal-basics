package com.narwhal.basics.integrations.notifications.client.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.LanguageItemDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.LanguageItemsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

@Singleton
public class LanguageItemsService {

    @Inject
    private LanguageItemsEndpoint languageItemsApi;
    @Inject
    private MemcachedService memcachedService;

    public ArrayList<LanguageItemDTO> getItems(String clientId, String versionId, String groupKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_LANGUAGE_ITEMS_KEYS, versionId, groupKey);
        ArrayList<LanguageItemDTO> groups = (ArrayList<LanguageItemDTO>) memcachedService.get(key);
        //
        if (groups == null) {
            groups = languageItemsApi.getItems(clientId, versionId, groupKey);
            memcachedService.put(key, groups);
        }
        return groups;
    }

    public List<LanguageItemDTO> updateItems(String clientId, String versionId, String groupKey, List<LanguageItemDTO> items) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(items, "items");
        //
        items = languageItemsApi.updateItems(clientId, versionId, groupKey, items);
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_LANGUAGE_GROUPS_KEYS, versionId);
        memcachedService.delete(key);
        //
        List<LanguageItemDTO> itemsFiltered = new ArrayList<>();
        for (LanguageItemDTO item : items) {
            if (StringUtils.equals(item.getGroupLanguageKey(), groupKey)) {
                // Group key was update, remove them because it was moved
                itemsFiltered.add(item);
            }
            key = String.format(MemcachedConstants.NOTIFICATION_LANGUAGE_ITEMS_KEYS, versionId, groupKey);
            memcachedService.delete(key);
        }
        //
        return itemsFiltered;
    }
}
