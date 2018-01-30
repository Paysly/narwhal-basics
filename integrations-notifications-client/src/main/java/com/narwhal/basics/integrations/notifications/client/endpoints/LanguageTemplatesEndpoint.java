package com.narwhal.basics.integrations.notifications.client.endpoints;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.LanguageTemplateDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.NotificationEndpointUnavailable;

@Singleton
public class LanguageTemplatesEndpoint extends BaseNarwhalApi {

    private String TEMPLATES_URL;
    private String TEMPLATE_URL;
    private MicroservicesContext microservicesContext;

    @Inject
    public LanguageTemplatesEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        TEMPLATES_URL = microservicesContext.getNotificationsEndpoint() + "/notification/%s/language/group/%s/items/%s/templates/";
        TEMPLATE_URL = TEMPLATES_URL + "%s";
    }

    public ArrayList<LanguageTemplateDTO> getTemplates(String clientId, String versionId, String groupKey, String languageKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(languageKey, "languageKey");
        //
        try {
            String url = String.format(TEMPLATES_URL, versionId, groupKey, languageKey);
            return securedGet(clientId, url, ArrayList.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification templates", e);
        }
    }

    public LanguageTemplateDTO getTemplate(String clientId, String versionId, String groupKey, String languageKey, String name) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(languageKey, "languageKey");
        ApiPreconditions.checkNotNull(name, "name");
        //
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, languageKey, name);
            return securedGet(clientId, url, LanguageTemplateDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to fetch notification template", e);
        }
    }

    public LanguageTemplateDTO createTemplate(String clientId, String versionId, String groupKey, String languageKey, LanguageTemplateDTO templateDTO) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(languageKey, "languageKey");
        ApiPreconditions.checkNotNull(templateDTO, "templateDTO");
        ApiPreconditions.checkNotNull(templateDTO.getTemplateName(), "templateDTO.templateName");
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, languageKey, templateDTO.getTemplateName());
            return securedPost(clientId, url, templateDTO, LanguageTemplateDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to create notification template", e);
        }
    }

    public LanguageTemplateDTO updateTemplate(String clientId, String versionId, String groupKey, String languageKey, LanguageTemplateDTO templateDTO) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(languageKey, "languageKey");
        ApiPreconditions.checkNotNull(templateDTO, "templateDTO");
        ApiPreconditions.checkNotNull(templateDTO.getTemplateName(), "templateDTO.templateName");
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, languageKey, templateDTO.getTemplateName());
            return securedPut(clientId, url, templateDTO, LanguageTemplateDTO.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to update notification template", e);
        }
    }

    public void deleteTemplate(String clientId, String versionId, String groupKey, String languageKey, String templateName) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(languageKey, "languageKey");
        ApiPreconditions.checkNotNull(templateName, "templateName");
        //
        try {
            String url = String.format(TEMPLATE_URL, versionId, groupKey, languageKey, templateName);
            securedDelete(clientId, url);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationEndpointUnavailable("Failed to delete notification template", e);
        }
    }
}
