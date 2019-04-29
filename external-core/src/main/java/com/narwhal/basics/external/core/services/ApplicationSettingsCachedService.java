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
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;

import java.util.Date;

@Singleton
public class ApplicationSettingsCachedService {

    @Inject
    private ApplicationSettingsService settingsService;
    @Inject
    private MemcachedService memcachedService;
    @Inject
    private MicroservicesContext microservicesContext;

    public ApplicationSettings getCachedApplicationSettings(ApplicationEnvironmentTypes environment) {
        ApiPreconditions.checkNotNull(environment, "environment");
        String namespaceId = environment.toString();
        //
        NamespaceManager.set(namespaceId);
        ApplicationSettings settings = (ApplicationSettings) memcachedService.getFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId());
        if (settings == null) {
            settings = settingsService.getApplicationSettings();
            memcachedService.putFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId(), settings);
        }
        NamespaceManager.set(null);
        return settings;
    }

    public void updateCachedApplicationSettings(ApplicationEnvironmentTypes environment, ApplicationSettings applicationSettings) {
        ApiPreconditions.checkNotNull(environment, "environment");
        String namespaceId = environment.toString();
        //
        NamespaceManager.set(namespaceId);
        settingsService.updateApplicationSettings(applicationSettings);
        memcachedService.putFilteringByNamespace(namespaceId, microservicesContext.getApplicationSettingsId(), applicationSettings);
        NamespaceManager.set(null);
    }

    public void updateSendgridData(ApplicationEnvironmentTypes environment, SendgridDataDTO sendgridDataDTO) {
        ApiPreconditions.checkNotNull(environment, "environment");
        ApiPreconditions.checkNotNull(sendgridDataDTO, "sendgridData");
        //
        ApplicationSettings applicationSettings = getCachedApplicationSettings(environment);
        //
        applicationSettings.setEmailSender(sendgridDataDTO.getEmailSender());
        applicationSettings.setSendgridMailUrl(sendgridDataDTO.getMailUrl());
        applicationSettings.setSendgridApiKey(sendgridDataDTO.getApiKey());
        applicationSettings.setSendgridAppUrl(sendgridDataDTO.getAppUrl());
        //
        applicationSettings.setUpdatedAt(new Date());
        //
        updateCachedApplicationSettings(environment, applicationSettings);
    }

    public void updateTwilioData(ApplicationEnvironmentTypes environment, TwilioDataDTO twilioDataDTO) {
        ApiPreconditions.checkNotNull(environment, "environment");
        ApiPreconditions.checkNotNull(twilioDataDTO, "twilioData");
        //
        ApplicationSettings applicationSettings = getCachedApplicationSettings(environment);
        //
        applicationSettings.setTwilioFromNumber(twilioDataDTO.getFromNumber());
        applicationSettings.setTwilioSid(twilioDataDTO.getSid());
        applicationSettings.setTwilioToken(twilioDataDTO.getToken());
        applicationSettings.setTwilioUrl(twilioDataDTO.getUrl());
        //
        applicationSettings.setUpdatedAt(new Date());
        //
        updateCachedApplicationSettings(environment, applicationSettings);
    }

    public void updateFirebaseData(ApplicationEnvironmentTypes environment, FirebaseDataDTO firebaseDataDTO) {
        ApiPreconditions.checkNotNull(environment, "environment");
        ApiPreconditions.checkNotNull(firebaseDataDTO, "firebaseData");
        //
        ApplicationSettings applicationSettings = getCachedApplicationSettings(environment);
        //
        applicationSettings.setFirebaseAppUrl(firebaseDataDTO.getAppUrl());
        applicationSettings.setFirebaseIconUrl(firebaseDataDTO.getIconUrl());
        applicationSettings.setFirebaseMessagingUrl(firebaseDataDTO.getMessagingUrl());
        applicationSettings.setFirebaseServerKey(firebaseDataDTO.getServerKey());
        applicationSettings.setFirebaseIosKey(firebaseDataDTO.getIosKey());
        applicationSettings.setFirebaseAndroidKey(firebaseDataDTO.getAndroidKey());
        //
        applicationSettings.setUpdatedAt(new Date());
        //
        updateCachedApplicationSettings(environment, applicationSettings);
    }
}
