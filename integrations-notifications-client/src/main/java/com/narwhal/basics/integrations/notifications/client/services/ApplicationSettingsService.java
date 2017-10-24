package com.narwhal.basics.integrations.notifications.client.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.integrations.notifications.client.dto.settings.FirebaseSettingsDTO;
import com.narwhal.basics.integrations.notifications.client.dto.settings.SendgridSettingsDTO;
import com.narwhal.basics.integrations.notifications.client.dto.settings.TwilioSettingsDTO;
import com.narwhal.basics.integrations.notifications.client.endpoints.ApplicationSettingsEndpoint;
import com.narwhal.basics.integrations.notifications.client.utils.MemcachedConstants;

@Singleton
public class ApplicationSettingsService {

    @Inject
    private ApplicationSettingsEndpoint applicationSettingsApi;
    @Inject
    private MemcachedService memcachedService;

    public SendgridSettingsDTO getSendgridSettings() {
        SendgridSettingsDTO settings = (SendgridSettingsDTO) memcachedService.get(MemcachedConstants.SENDGRID_KEY);
        if (settings == null) {
            settings = applicationSettingsApi.getSendgridSettings();
            memcachedService.put(MemcachedConstants.SENDGRID_KEY, settings);
        }
        //
        return settings;
    }

    public void updateSendgridSettings(SendgridSettingsDTO settings) {
        ApiPreconditions.checkNotNull(settings, "settings");
        //
        memcachedService.delete(MemcachedConstants.SENDGRID_KEY);
        applicationSettingsApi.setSendgridSettings(settings);
        memcachedService.put(MemcachedConstants.SENDGRID_KEY, settings);
    }

    public TwilioSettingsDTO getTwilioSettings() {
        //
        TwilioSettingsDTO settings = (TwilioSettingsDTO) memcachedService.get(MemcachedConstants.TWILIO_KEY);
        if (settings == null) {
            settings = applicationSettingsApi.getTwilioSettings();
            memcachedService.put(MemcachedConstants.TWILIO_KEY, settings);
        }
        //
        return settings;
    }

    public void updateTwilioSettings(TwilioSettingsDTO settings) {
        ApiPreconditions.checkNotNull(settings, "settings");
        //
        memcachedService.delete(MemcachedConstants.TWILIO_KEY);
        applicationSettingsApi.setTwilioSettings(settings);
        memcachedService.put(MemcachedConstants.TWILIO_KEY, settings);
    }

    public FirebaseSettingsDTO getFirebaseSettings() {
        //
        FirebaseSettingsDTO settings = (FirebaseSettingsDTO) memcachedService.get(MemcachedConstants.FIREBASE_KEY);
        if (settings == null) {
            settings = applicationSettingsApi.getFirebaseSettings();
            memcachedService.put(MemcachedConstants.FIREBASE_KEY, settings);
        }
        //
        return settings;
    }

    public void updateFirebaseSettings(FirebaseSettingsDTO settings) {
        ApiPreconditions.checkNotNull(settings, "settings");
        //
        memcachedService.delete(MemcachedConstants.FIREBASE_KEY);
        applicationSettingsApi.setFirebaseSettings(settings);
        memcachedService.put(MemcachedConstants.FIREBASE_KEY, settings);
    }
}
