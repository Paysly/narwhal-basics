package com.narwhal.basics.integrations.notifications.client.endpoints;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.api.BaseNarwhalApi;
import com.narwhal.basics.integrations.notifications.client.dto.settings.FirebaseSettingsDTO;
import com.narwhal.basics.integrations.notifications.client.dto.settings.SendgridSettingsDTO;
import com.narwhal.basics.integrations.notifications.client.dto.settings.TwilioSettingsDTO;
import com.narwhal.basics.integrations.notifications.client.exceptions.ApplicationSettingsUnavailable;

@Singleton
public class ApplicationSettingsEndpoint extends BaseNarwhalApi {

    private String SETTINGS_SENDGRID_URL;
    private String SETTINGS_TWILIO_URL;
    private String SETTINGS_FIREBASE_URL;

    private MicroservicesContext microservicesContext;

    @Inject
    public ApplicationSettingsEndpoint(MicroservicesContext microservicesContext) {
        this.microservicesContext = microservicesContext;
        //
        SETTINGS_SENDGRID_URL = microservicesContext.getNotificationsEndpoint() + "/application/settings/sendgrid";
        SETTINGS_TWILIO_URL = microservicesContext.getNotificationsEndpoint() + "/application/settings/twilio";
        SETTINGS_FIREBASE_URL = microservicesContext.getNotificationsEndpoint() + "/application/settings/firebase";
    }

    public SendgridSettingsDTO getSendgridSettings(String clientId) {
        try {
            return securedGet(clientId, SETTINGS_SENDGRID_URL, SendgridSettingsDTO.class);
        } catch (Exception e) {
            throw new ApplicationSettingsUnavailable("Failed to fetch sendgrid settings", e);
        }
    }

    public void setSendgridSettings(String clientId, SendgridSettingsDTO sendgridSettings) {
        ApiPreconditions.checkNotNull(sendgridSettings, "sendgridSettings");
        try {
            securedPost(clientId, SETTINGS_SENDGRID_URL, sendgridSettings, null);
        } catch (Exception e) {
            throw new ApplicationSettingsUnavailable("Failed to update sendgrid settings", e);
        }
    }

    public TwilioSettingsDTO getTwilioSettings(String clientId) {
        try {
            return securedGet(clientId, SETTINGS_TWILIO_URL, TwilioSettingsDTO.class);
        } catch (Exception e) {
            throw new ApplicationSettingsUnavailable("Failed to fetch twilio settings", e);
        }
    }

    public void setTwilioSettings(String clientId, TwilioSettingsDTO twilioSettings) {
        ApiPreconditions.checkNotNull(twilioSettings, "twilioSettings");
        try {
            securedPost(clientId, SETTINGS_TWILIO_URL, twilioSettings, null);
        } catch (Exception e) {
            throw new ApplicationSettingsUnavailable("Failed to update twilio settings", e);
        }
    }

    public FirebaseSettingsDTO getFirebaseSettings(String clientId) {
        try {
            return securedGet(clientId, SETTINGS_FIREBASE_URL, FirebaseSettingsDTO.class);
        } catch (Exception e) {
            throw new ApplicationSettingsUnavailable("Failed to fetch firebase settings", e);
        }
    }

    public void setFirebaseSettings(String clientId, FirebaseSettingsDTO firebaseSettings) {
        ApiPreconditions.checkNotNull(firebaseSettings, "firebaseSettings");
        try {
            securedPost(clientId, SETTINGS_FIREBASE_URL, firebaseSettings, null);
        } catch (Exception e) {
            throw new ApplicationSettingsUnavailable("Failed to update firebase settings", e);
        }
    }
}
