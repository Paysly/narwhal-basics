package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationTemplateDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;

import java.util.ArrayList;

@Singleton
public class NotificationTemplatesEndpoint extends BaseNarwhalApi {

    private String TEMPLATES_URL;
    private String TEMPLATE_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public NotificationTemplatesEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        TEMPLATES_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/group/%s/items/%s/templates/";
        TEMPLATE_URL = TEMPLATES_URL + "%s/%s";
    }

    public ArrayList<NotificationTemplateDTO> getTemplates(String clientId, String versionId, String groupKey, String notificationKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(notificationKey, "notificationKey");
        //
        try {
            String url = String.format(TEMPLATES_URL, versionId, groupKey, notificationKey);
            return securedGet(clientId, url, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification templates", e);
        }
    }

    public NotificationTemplateDTO getTemplate(String clientId, String versionId, String groupKey, String notificationKey, NotificationMechanismType mechanismType, String name) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(notificationKey, "notificationKey");
        ApiPreconditions.checkNotNull(mechanismType, "mechanismType");
        ApiPreconditions.checkNotNull(name, "name");
        //
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, notificationKey, mechanismType, name);
            return securedGet(clientId, url, NotificationTemplateDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification template", e);
        }
    }

    public NotificationTemplateDTO createTemplate(String clientId, String versionId, String groupKey, String notificationKey, NotificationTemplateDTO templateDTO) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(notificationKey, "notificationKey");
        ApiPreconditions.checkNotNull(templateDTO, "templateDTO");
        ApiPreconditions.checkNotNull(templateDTO.getMechanismType(), "templateDTO,mechanismType");
        ApiPreconditions.checkNotNull(templateDTO.getTemplateName(), "templateDTO.templateName");
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, notificationKey, templateDTO.getMechanismType(), templateDTO.getTemplateName());
            return securedPost(clientId, url, templateDTO, NotificationTemplateDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to create notification template", e);
        }
    }

    public NotificationTemplateDTO updateTemplate(String clientId, String versionId, String groupKey, String notificationKey, NotificationTemplateDTO templateDTO) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(notificationKey, "notificationKey");
        ApiPreconditions.checkNotNull(templateDTO, "templateDTO");
        ApiPreconditions.checkNotNull(templateDTO.getMechanismType(), "templateDTO,mechanismType");
        ApiPreconditions.checkNotNull(templateDTO.getTemplateName(), "templateDTO.templateName");
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, notificationKey, templateDTO.getMechanismType(), templateDTO.getTemplateName());
            return securedPut(clientId, url, templateDTO, NotificationTemplateDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification template", e);
        }
    }

    public void deleteTemplate(String clientId, String versionId, String groupKey, String notificationKey, NotificationMechanismType mechanismName, String templateName) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(notificationKey, "notificationKey");
        ApiPreconditions.checkNotNull(mechanismName, "mechanismName");
        ApiPreconditions.checkNotNull(templateName, "templateName");
        //
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, notificationKey, mechanismName, templateName);
            securedDelete(clientId, url);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to delete notification template", e);
        }
    }
}
