package com.narwhal.basics.integrations.notifications.client.services;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.LanguageTemplateDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.LanguageTemplatesEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

@Singleton
public class LanguageTemplatesService {

    @Inject
    private LanguageTemplatesEndpoint languageTemplatesApi;
    @Inject
    private MemcachedService memcachedService;

    private String getMemcachedKey(String versionId, String groupKey, String languageKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(languageKey, "languageKey");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_LANGUAGE_TEMPLATES_KEYS, versionId, groupKey, languageKey);
        return key;
    }

    private String getMemcachedKey(String versionId, String groupKey, String languageKey, String templateName) {
        String key = getMemcachedKey(versionId, groupKey, languageKey);
        key = String.format(key + "_%s", templateName);
        return key;
    }

    public ArrayList<LanguageTemplateDTO> getTemplates(String clientId, String versionId, String groupKey, String languageKey) {
        String keys = getMemcachedKey(versionId, groupKey, languageKey);
        ArrayList<LanguageTemplateDTO> templates = (ArrayList) memcachedService.get(keys);
        //
        if (templates == null) {
            templates = languageTemplatesApi.getTemplates(clientId, versionId, groupKey, languageKey);
            memcachedService.put(keys, templates);
        }
        return templates;
    }

    public LanguageTemplateDTO getTemplate(String clientId, String versionId, String groupKey, String languageKey, String templateName) {
        String key = getMemcachedKey(versionId, groupKey, languageKey, templateName);
        LanguageTemplateDTO template = (LanguageTemplateDTO) memcachedService.get(key);
        //
        if (template == null) {
            template = languageTemplatesApi.getTemplate(clientId, versionId, groupKey, languageKey, templateName);
            memcachedService.put(key, template);
        }
        return template;
    }

    public LanguageTemplateDTO createTemplate(String clientId, String versionId, String groupKey, String languageKey, LanguageTemplateDTO template) {
        String keys = getMemcachedKey(versionId, groupKey, languageKey);
        String key = getMemcachedKey(versionId, groupKey, languageKey, template.getTemplateName());
        //
        template = languageTemplatesApi.createTemplate(clientId, versionId, groupKey, languageKey, template);
        //
        ArrayList<LanguageTemplateDTO> templates = (ArrayList) memcachedService.get(keys);
        //
        if (templates != null) {
            // cache version found. Add new one to it.
            templates.add(template);
            memcachedService.put(keys, templates);
        }
        // Add new one to unique cache
        memcachedService.put(key, template);
        //
        return template;
    }

    public LanguageTemplateDTO updateTemplate(String clientId, String versionId, String groupKey, String languageKey, LanguageTemplateDTO template) {
        String keys = getMemcachedKey(versionId, groupKey, languageKey);
        String key = getMemcachedKey(versionId, groupKey, languageKey, template.getTemplateName());
        //
        template = languageTemplatesApi.updateTemplate(clientId, versionId, groupKey, languageKey, template);
        //
        memcachedService.delete(keys);
        // Add new one to unique cache
        memcachedService.put(key, template);
        //
        return template;
    }

    public void deleteTemplate(String clientId, String versionId, String groupKey, String languageKey, String templateName) {
        String keys = getMemcachedKey(versionId, groupKey, languageKey);
        String key = getMemcachedKey(versionId, groupKey, languageKey, templateName);
        //
        languageTemplatesApi.deleteTemplate(clientId, versionId, groupKey, languageKey, templateName);
        //
        // Remove from unique cache
        memcachedService.delete(keys);
        memcachedService.delete(key);
    }
}
