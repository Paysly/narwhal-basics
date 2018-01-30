package com.narwhal.basics.integrations.notifications.client.services;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.LanguageGroupDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.LanguageGroupsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

@Singleton
public class LanguageGroupsService {

    @Inject
    private LanguageGroupsEndpoint languageGroupsEndpoint;
    @Inject
    private MemcachedService memcachedService;

    public ArrayList<LanguageGroupDTO> getGroups(String clientId, String versionId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_LANGUAGE_GROUPS_KEYS, versionId);
        ArrayList<LanguageGroupDTO> groups = (ArrayList<LanguageGroupDTO>) memcachedService.get(key);
        //
        if (groups == null) {
            groups = languageGroupsEndpoint.getGroups(clientId, versionId);
            memcachedService.put(key, groups);
        }
        return groups;
    }

    public ArrayList<LanguageGroupDTO> updateGroups(String clientId, String versionId, ArrayList<LanguageGroupDTO> groups) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groups, "groups");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_LANGUAGE_GROUPS_KEYS, versionId);
        groups = languageGroupsEndpoint.updateGroups(clientId, versionId, groups);
        memcachedService.delete(key);
        memcachedService.put(key, groups);
        //
        return groups;
    }
}
