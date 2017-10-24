package com.narwhal.basics.integrations.notifications.client.dto.notifications;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationTemplateDTO implements Serializable {

    private String id;
    //
    private String templateName; // This will be primary used as language code
    //
    private String versionId;
    //
    private String notificationKey;
    //
    private NotificationMechanismType mechanismType;
    //
    private String title;
    //
    private String bodyHtml;
    //
    private String bodyPlain;
    //
    private String pushActionLink;
    //
    private String headerTemplateId;
    //
    private String footerTemplateId;
    //
    private boolean requireVerifiedSource;
    //
    private Date createdAt;
    //
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public NotificationMechanismType getMechanismType() {
        return mechanismType;
    }

    public void setMechanismType(NotificationMechanismType mechanismType) {
        this.mechanismType = mechanismType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public String getBodyPlain() {
        return bodyPlain;
    }

    public void setBodyPlain(String bodyPlain) {
        this.bodyPlain = bodyPlain;
    }

    public String getPushActionLink() {
        return pushActionLink;
    }

    public void setPushActionLink(String pushActionLink) {
        this.pushActionLink = pushActionLink;
    }

    public String getHeaderTemplateId() {
        return headerTemplateId;
    }

    public void setHeaderTemplateId(String headerTemplateId) {
        this.headerTemplateId = headerTemplateId;
    }

    public String getFooterTemplateId() {
        return footerTemplateId;
    }

    public void setFooterTemplateId(String footerTemplateId) {
        this.footerTemplateId = footerTemplateId;
    }

    public boolean isRequireVerifiedSource() {
        return requireVerifiedSource;
    }

    public void setRequireVerifiedSource(boolean requireVerifiedSource) {
        this.requireVerifiedSource = requireVerifiedSource;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
