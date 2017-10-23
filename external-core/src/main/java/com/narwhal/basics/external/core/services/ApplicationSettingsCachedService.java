package com.narwhal.basics.external.core.services;

import com.google.appengine.api.NamespaceManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.external.core.dto.FirebaseDataDTO;
import com.narwhal.basics.external.core.dto.SendgridDataDTO;
import com.narwhal.basics.external.core.dto.TwilioDataDTO;
import com.narwhal.basics.external.core.model.ApplicationSettings;

import java.util.Date;

@Singleton
public class ApplicationSettingsCachedService {

    @Inject
    private ApplicationSettingsService settingsService;
    @Inject
    private MemcachedService memcachedService;
    @Inject
    private MicroservicesContext microservicesContext;

    public ApplicationSettings getCachedApplicationSettings(String namespaceId) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        NamespaceManager.set(namespaceId);
        ApplicationSettings settings = (ApplicationSettings) memcachedService.getFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId());
        if (settings == null) {
            settings = settingsService.getApplicationSettings();
            memcachedService.putFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId(), settings);
        }
        NamespaceManager.set(null);
        return settings;
    }

    public void updateCachedApplicationSettings(String namespaceId, ApplicationSettings applicationSettings) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        NamespaceManager.set(namespaceId);
        settingsService.updateApplicationSettings(applicationSettings);
        memcachedService.putFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId(), applicationSettings);
        NamespaceManager.set(null);
    }

    public void updateSendgridData(String namespaceId, SendgridDataDTO sendgridDataDTO) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        ApiPreconditions.checkNotNull(sendgridDataDTO, "sendgridData");
        //
        ApplicationSettings applicationSettings = getCachedApplicationSettings(namespaceId);
        //
        applicationSettings.setEmailSender(sendgridDataDTO.getEmailSender());
        applicationSettings.setSendgridMailUrl(sendgridDataDTO.getMailUrl());
        applicationSettings.setSendgridApiKey(sendgridDataDTO.getApiKey());
        applicationSettings.setSendgridAppUrl(sendgridDataDTO.getAppUrl());
        //
        applicationSettings.setUpdatedAt(new Date());
        //
        updateCachedApplicationSettings(namespaceId, applicationSettings);
    }

    public void updateTwilioData(String namespaceId, TwilioDataDTO twilioDataDTO) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        ApiPreconditions.checkNotNull(twilioDataDTO, "twilioData");
        //
        ApplicationSettings applicationSettings = getCachedApplicationSettings(namespaceId);
        //
        applicationSettings.setTwilioFromNumber(twilioDataDTO.getFromNumber());
        applicationSettings.setTwilioSid(twilioDataDTO.getSid());
        applicationSettings.setTwilioToken(twilioDataDTO.getToken());
        applicationSettings.setTwilioUrl(twilioDataDTO.getUrl());
        //
        applicationSettings.setUpdatedAt(new Date());
        //
        updateCachedApplicationSettings(namespaceId, applicationSettings);
    }

    public void updateFirebaseData(String namespaceId, FirebaseDataDTO firebaseDataDTO) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        ApiPreconditions.checkNotNull(firebaseDataDTO, "firebaseData");
        //
        ApplicationSettings applicationSettings = getCachedApplicationSettings(namespaceId);
        //
        applicationSettings.setFirebaseAppUrl(firebaseDataDTO.getAppUrl());
        applicationSettings.setFirebaseIconUrl(firebaseDataDTO.getIconUrl());
        applicationSettings.setFirebaseMessagingUrl(firebaseDataDTO.getMessagingUrl());
        applicationSettings.setFirebaseServerKey(firebaseDataDTO.getServerKey());
        //
        applicationSettings.setUpdatedAt(new Date());
        //
        updateCachedApplicationSettings(namespaceId, applicationSettings);
    }
}
