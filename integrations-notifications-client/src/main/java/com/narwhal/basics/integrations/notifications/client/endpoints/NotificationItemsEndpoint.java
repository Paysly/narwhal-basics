package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationItemDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class NotificationItemsEndpoint extends BaseNarwhalApi {

    private String ITEMS_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationItemsEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        ITEMS_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/group/%s/items";
    }

    public ArrayList<NotificationItemDTO> getItems(String versionId, String groupKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        //
        try {
            String url = String.format(ITEMS_URL, versionId, groupKey);
            return securedGet(url, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification items", e);
        }
    }

    public List<NotificationItemDTO> updateItems(String versionId, String groupKey, List<NotificationItemDTO> items) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(items, "items");
        try {
            String url = String.format(ITEMS_URL, versionId, groupKey);
            return Arrays.asList(securedPut(url, new ArrayList<>(items), NotificationItemDTO[].class));
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification items", e);
        }
    }
}
