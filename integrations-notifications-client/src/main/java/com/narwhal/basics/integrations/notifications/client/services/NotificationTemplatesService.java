package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.notifications.NotificationTemplateDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.NotificationTemplatesEndpoint;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

import java.util.ArrayList;

@Singleton
public class NotificationTemplatesService {
    @Inject
    private NotificationTemplatesEndpoint notificationTemplatesApi;
    @Inject
    private MemcachedService memcachedService;

    private String getMemcachedKey(String versionId, String groupKey, String notificationKey) {
        ApiPreconditions.checkNotNull(versionId, "versionId");
        ApiPreconditions.checkNotNull(groupKey, "groupKey");
        ApiPreconditions.checkNotNull(notificationKey, "notificationKey");
        //
        String key = String.format(MemcachedConstants.NOTIFICATION_TEMPLATES_KEYS, versionId, groupKey, notificationKey);
        return key;
    }

    private String getMemcachedKey(String versionId, String groupKey, String notificationKey, String templateName, NotificationMechanismType mechanismType) {
        String key = getMemcachedKey(versionId, groupKey, notificationKey);
        key = String.format(key + "_%s_%s", templateName, mechanismType);
        return key;
    }

    public ArrayList<NotificationTemplateDTO> getTemplates(String clientId, String versionId, String groupKey, String notificationKey) {
        String keys = getMemcachedKey(versionId, groupKey, notificationKey);
        ArrayList<NotificationTemplateDTO> templates = (ArrayList) memcachedService.get(keys);
        //
        if (templates == null) {
            templates = notificationTemplatesApi.getTemplates(clientId, versionId, groupKey, notificationKey);
            memcachedService.put(keys, templates);
        }
        return templates;
    }

    public NotificationTemplateDTO getTemplate(String clientId, String versionId, String groupKey, String notificationKey, String templateName, NotificationMechanismType mechanismType) {
        String key = getMemcachedKey(versionId, groupKey, notificationKey, templateName, mechanismType);
        NotificationTemplateDTO template = (NotificationTemplateDTO) memcachedService.get(key);
        //
        if (template == null) {
            template = notificationTemplatesApi.getTemplate(clientId, versionId, groupKey, notificationKey, mechanismType, templateName);
            memcachedService.put(key, template);
        }
        return template;
    }


    public NotificationTemplateDTO createTemplate(String clientId, String versionId, String groupKey, String notificationKey, NotificationTemplateDTO template) {
        String keys = getMemcachedKey(versionId, groupKey, notificationKey);
        String key = getMemcachedKey(versionId, groupKey, notificationKey, template.getTemplateName(), template.getMechanismType());
        //
        template = notificationTemplatesApi.createTemplate(clientId, versionId, groupKey, notificationKey, template);
        //
        ArrayList<NotificationTemplateDTO> templates = (ArrayList) memcachedService.get(keys);
        //
        if (templates != null) {
            //cache version found. Add new one to it.
            templates.add(template);
            memcachedService.put(keys, templates);
        }
        //Add new one to unique cache
        memcachedService.put(key, template);
        //
        return template;
    }

    public NotificationTemplateDTO updateTemplate(String clientId, String versionId, String groupKey, String notificationKey, NotificationTemplateDTO template) {
        String keys = getMemcachedKey(versionId, groupKey, notificationKey);
        String key = getMemcachedKey(versionId, groupKey, notificationKey, template.getTemplateName(), template.getMechanismType());
        //
        template = notificationTemplatesApi.updateTemplate(clientId, versionId, groupKey, notificationKey, template);
        //
        memcachedService.delete(keys);
        //Add new one to unique cache
        memcachedService.put(key, template);
        //
        return template;
    }

    public void deleteTemplate(String clientId, String versionId, String groupKey, String notificationKey, String templateName, NotificationMechanismType templateMechanism) {
        String keys = getMemcachedKey(versionId, groupKey, notificationKey);
        String key = getMemcachedKey(versionId, groupKey, notificationKey, templateName, templateMechanism);
        //
        notificationTemplatesApi.deleteTemplate(clientId, versionId, groupKey, notificationKey, templateMechanism, templateName);
        //
        //Remove from unique cache
        memcachedService.delete(keys);
        memcachedService.delete(key);
    }
}
