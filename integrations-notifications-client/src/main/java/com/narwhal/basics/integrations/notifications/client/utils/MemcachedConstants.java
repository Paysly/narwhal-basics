package com.narwhal.basics.integrations.notifications.client.utils;

public interface MemcachedConstants {
    String SENDGRID_KEY = "SENDGRID_KEY";
    String FIREBASE_KEY = "FIREBASE_KEY";
    String TWILIO_KEY = "TWILIO_KEY";
    //
    String NOTIFICATION_VERSION_KEYS = "NOTIFICATION_VERSIONS";
    String NOTIFICATION_GROUPS_KEYS = "NOTIFICATION_%s_GROUPS";
    String NOTIFICATION_ITEMS_KEYS = "NOTIFICATION_%s_%s_ITEMS";
    String NOTIFICATION_TEMPLATES_KEYS = "NOTIFICATION_%s_%s_%sTEMPLATES";
    String NOTIFICATION_LAYOUTS_KEYS = "NOTIFICATION_%s_LAYOUTS";
    String ENVIRONMENT_VARIABLES_KEY = "ENVIRONMENT_VARIABLES_KEY";
}
