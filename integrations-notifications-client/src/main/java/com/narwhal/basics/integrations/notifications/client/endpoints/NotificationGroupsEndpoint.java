package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationGroupDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;

import java.util.ArrayList;

@Singleton
public class NotificationGroupsEndpoint extends BaseNarwhalApi {

    private String GROUPS_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationGroupsEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        GROUPS_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/group/";
    }

    public ArrayList<NotificationGroupDTO> getGroups(String clientId, String versionId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        //
        try {
            String url = String.format(GROUPS_URL, versionId);
            return securedGet(clientId, url, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification groups", e);
        }
    }

    public ArrayList<NotificationGroupDTO> updateGroups(String clientId, String versionId, ArrayList<NotificationGroupDTO> groups) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groups, "groups");
        try {
            String url = String.format(GROUPS_URL, versionId);
            return securedPut(clientId, url, groups, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification groups", e);
        }
    }
}
