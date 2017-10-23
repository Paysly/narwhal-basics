package com.narwhal.basics.external.core.model;

public interface SendgridSettings {
    String getEmailSender();

    String getSendgridApiKey();

    String getSendgridMailUrl();

    String getSendgridAppUrl();

    void checkSendgridData();
}
