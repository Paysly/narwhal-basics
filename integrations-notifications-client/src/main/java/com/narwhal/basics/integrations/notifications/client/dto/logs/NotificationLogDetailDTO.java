package com.narwhal.basics.integrations.notifications.client.dto.logs;

import java.io.Serializable;
import java.util.Date;

public class NotificationLogDetailDTO implements Serializable {

    private String id;
    private String emailTitle;
    private String emailBodyHtml;
    private String smsText;
    private String pushTitle;
    private String pushBody;
    private Date createdAt;
    private String email;
    private String phone;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailBodyHtml() {
        return emailBodyHtml;
    }

    public void setEmailBodyHtml(String emailBodyHtml) {
        this.emailBodyHtml = emailBodyHtml;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public String getPushBody() {
        return pushBody;
    }

    public void setPushBody(String pushBody) {
        this.pushBody = pushBody;
    }
}

