package com.narwhal.basics.core.rest.utils;

import java.util.HashMap;
import java.util.Map;

import static com.narwhal.basics.core.rest.utils.WebConstants.LOCALHOST;

public abstract class MicroservicesContext {

    // External Settings
    private String applicationSettingsId;
    // Authorization
    private Map<String, String> clientIdSecret = new HashMap<>();
    // Endpoints
    private String authorizationEndpoint;
    private String notificationsEndpoint;
    private String mainAppEndpoint;
    private String adminEndpoint;
    private String stagingAdminEndpoint;
    //

    public String getApplicationSettingsId() {
        return applicationSettingsId;
    }

    public void setApplicationSettingsId(String applicationSettingsId) {
        this.applicationSettingsId = applicationSettingsId;
    }

    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getNotificationsEndpoint() {
        return notificationsEndpoint;
    }

    public void setNotificationsEndpoint(String notificationsEndpoint) {
        this.notificationsEndpoint = notificationsEndpoint;
    }

    public String getMainAppEndpoint() {
        return mainAppEndpoint;
    }

    public void setMainAppEndpoint(String mainAppEndpoint) {
        this.mainAppEndpoint = mainAppEndpoint;
    }

    public String getAdminEndpoint() {
        return adminEndpoint;
    }

    public void setAdminEndpoint(String adminEndpoint) {
        this.adminEndpoint = adminEndpoint;
    }

    public String getStagingAdminEndpoint() {
        return stagingAdminEndpoint;
    }

    public void setStagingAdminEndpoint(String stagingAdminEndpoint) {
        this.stagingAdminEndpoint = stagingAdminEndpoint;
    }

    public String getLocalBaseServerUrl() {
        return LOCALHOST;
    }

    public Map<String, String> getClientIdSecret() {
        return clientIdSecret;
    }

    public void setClientIdSecret(Map<String, String> clientIdSecret) {
        this.clientIdSecret = clientIdSecret;
    }

    public abstract String getStagingBaseServerUrl();

    public abstract String getProductionBaseServerUrl();


}
