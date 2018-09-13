package com.narwhal.basics.integrations.notifications.client.endpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.LanguageItemDTO;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.LanguageTemplateDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;

@Singleton
public class LanguageItemsEndpoint extends BaseNarwhalApi {

    private String ITEMS_URL;
    private MicroservicesContext microservicesContext;

    @Inject
    public LanguageItemsEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        ITEMS_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/language/group/%s/items";
    }

    public ArrayList<LanguageItemDTO> getItems(String clientId, String versionId, String groupKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        //
        try {
            String url = String.format(ITEMS_URL, versionId, groupKey);
            return securedGet(clientId, url, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification items", e);
        }
    }

    public List<LanguageItemDTO> updateItems(String clientId, String versionId, String groupKey, List<LanguageItemDTO> items) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(items, "items");
        try {
            String url = String.format(ITEMS_URL, versionId, groupKey);
            return Arrays.asList(securedPut(clientId, url, new ArrayList<>(items), LanguageItemDTO[].class));
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification items", e);
        }
    }

    public ArrayList<LanguageTemplateDTO> getTemplates(String clientId, String versionId, String groupKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        //
        try {
            String url = String.format(this.ITEMS_URL, versionId, groupKey) + "/templates";
            return (ArrayList) this.securedGet(clientId, url, ArrayList.class);
        } catch (ApiException var5) {
            throw var5;
        } catch (Exception var6) {
            throw new NotificationEndpointUnavailable("Failed to get language templates", var6);
        }
    }

    public List<LanguageTemplateDTO> updateTemplates(String clientId, String versionId, String groupKey, List<LanguageTemplateDTO> templates) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(templates, "templates");

        try {
            String url = String.format(this.ITEMS_URL, versionId, groupKey) + "/templates";
            return Arrays.asList((LanguageTemplateDTO[]) this.securedPut(clientId, url, new ArrayList(templates), LanguageTemplateDTO[].class));
        } catch (ApiException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new NotificationEndpointUnavailable("Failed to update language templates", var7);
        }
    }
}
