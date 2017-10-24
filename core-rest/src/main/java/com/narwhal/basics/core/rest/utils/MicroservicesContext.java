package com.narwhal.basics.core.rest.utils;

public class MicroservicesContext {

    // External Settings
    private String applicationSettingsId;
    // Authorization
    private String clientId;
    private String clientSecret;
    // Endpoints
    private String authorizationEndpoint;
    private String notificationsEndpoint;
    private String mainAppEndpoint;
    private String adminEndpoint;
    private String stagingAdminEndpoint;

    public String getApplicationSettingsId() {
        return applicationSettingsId;
    }

    public void setApplicationSettingsId(String applicationSettingsId) {
        this.applicationSettingsId = applicationSettingsId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
}
