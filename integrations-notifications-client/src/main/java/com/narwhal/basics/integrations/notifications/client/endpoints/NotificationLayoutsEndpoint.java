package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationLayoutDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;
import com.narwhal.basics.integrations.notifications.client.types.NotificationLayoutType;

import java.util.ArrayList;

@Singleton
public class NotificationLayoutsEndpoint extends BaseNarwhalApi {

    private String LAYOUTS_URL;
    private String LAYOUT_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationLayoutsEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        LAYOUTS_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/layout/";
        LAYOUT_URL = LAYOUTS_URL + "%s";
    }

    public ArrayList<NotificationLayoutDTO> getLayouts(String clientId, String versionId) {
        return getLayouts(clientId, versionId, null);
    }

    public ArrayList<NotificationLayoutDTO> getLayouts(String clientId, String versionId, NotificationLayoutType layoutType) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        //
        try {
            String url = String.format(LAYOUTS_URL, versionId);
            //
            if (layoutType != null) {
                url += "?layoutType=" + layoutType;
            }
            //
            return securedGet(clientId, url, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification layouts", e);
        }
    }

    public NotificationLayoutDTO getLayout(String clientId, String versionId, String layoutId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(layoutId, "layoutId");
        //
        try {
            String url = String.format(LAYOUT_URL, versionId, layoutId);
            return securedGet(clientId, url, NotificationLayoutDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification layout", e);
        }
    }

    public NotificationLayoutDTO createLayout(String clientId, String versionId, NotificationLayoutDTO layoutDTO) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(layoutDTO, "layoutDTO");
        try {
            String url = String.format(LAYOUTS_URL, versionId);
            return securedPost(clientId, url, layoutDTO, NotificationLayoutDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to create notification layout", e);
        }
    }

    public NotificationLayoutDTO updateLayout(String clientId, String versionId, NotificationLayoutDTO layoutDTO) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(layoutDTO, "layoutDTO");
        ApiPreconditions.checkNotNull(layoutDTO.getId(), "layoutDTO.id");
        try {
            String url = String.format(LAYOUT_URL, versionId, layoutDTO.getId());
            return securedPut(clientId, url, layoutDTO, NotificationLayoutDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification layout", e);
        }
    }

    public void deleteLayout(String clientId, String versionId, String layoutId) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(layoutId, "layoutId");
        try {
            String url = String.format(LAYOUT_URL, versionId, layoutId);
            securedDelete(clientId, url);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to delete notification layout", e);
        }
    }
}
