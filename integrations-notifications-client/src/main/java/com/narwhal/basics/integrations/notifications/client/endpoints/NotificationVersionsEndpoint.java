package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationVersionDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;

import java.util.List;

@Singleton
public class NotificationVersionsEndpoint extends BaseNarwhalApi {

    private String VERSIONS_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationVersionsEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        VERSIONS_URL = microservicesContext.getNotificationsEndpoint() + "/notification-version/";
    }

    public List<NotificationVersionDTO> getVersions(String clientId) {
        try {
            return securedGet(clientId,VERSIONS_URL, List.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification versions", e);
        }
    }

    public NotificationVersionDTO createVersion(String clientId, NotificationVersionDTO version) {
        ApiPreconditions.checkNotNull(version, "version");
        try {
            return securedPost(clientId,VERSIONS_URL, version, NotificationVersionDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to create notification version", e);
        }
    }

    public NotificationVersionDTO updateVersion(String clientId, NotificationVersionDTO version) {
        ApiPreconditions.checkNotNull(version, "version");
        ApiPreconditions.checkNotNull(version.getId(), "version.id");
        try {
            return securedPut(clientId, VERSIONS_URL + version.getId(), version, NotificationVersionDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification version", e);
        }
    }

    public void deleteVersion(String clientId, String versionId) {
        try {
            securedDelete(clientId, VERSIONS_URL + versionId);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to delete notification version", e);
        }
    }
}
