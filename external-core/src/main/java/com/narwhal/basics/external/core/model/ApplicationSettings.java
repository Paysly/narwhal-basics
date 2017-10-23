package com.narwhal.basics.external.core.model;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.exceptions.FirebaseProviderInfoNotConfiguredException;
import com.narwhal.basics.external.core.exceptions.SendgridProviderInfoNotConfiguredException;
import com.narwhal.basics.external.core.exceptions.TwilioProviderInfoNotConfiguredException;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@Entity
@Index
public class ApplicationSettings implements BaseModel, SendgridSettings, FirebaseSettings {

    @Id
    private String id;

    @Unindex
    @IgnoreSave(IfDefault.class)
    private String emailSender;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String sendgridApiKey;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String sendgridMailUrl;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String sendgridAppUrl;

    @Unindex
    @IgnoreSave(IfDefault.class)
    private String twilioFromNumber;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String twilioSid;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String twilioToken;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String twilioUrl;

    @Unindex
    @IgnoreSave(IfDefault.class)
    private String firebaseServerKey;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String firebaseMessagingUrl;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String firebaseAppUrl;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String firebaseIconUrl;

    private Date createdAt;
    @Unindex
    private Date updatedAt;

    public void init(String id) {
        ApiPreconditions.checkNotNull(id, "id");
        //
        this.id = id;
        //
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getSendgridApiKey() {
        return sendgridApiKey;
    }

    public void setSendgridApiKey(String sendgridApiKey) {
        this.sendgridApiKey = sendgridApiKey;
    }

    public String getSendgridMailUrl() {
        return sendgridMailUrl;
    }

    public void setSendgridMailUrl(String sendgridMailUrl) {
        this.sendgridMailUrl = sendgridMailUrl;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSendgridAppUrl() {
        return sendgridAppUrl;
    }

    public void setSendgridAppUrl(String sendgridAppUrl) {
        this.sendgridAppUrl = sendgridAppUrl;
    }

    public String getTwilioFromNumber() {
        return twilioFromNumber;
    }

    public void setTwilioFromNumber(String twilioFromNumber) {
        this.twilioFromNumber = twilioFromNumber;
    }

    public String getTwilioSid() {
        return twilioSid;
    }

    public void setTwilioSid(String twilioSid) {
        this.twilioSid = twilioSid;
    }

    public String getTwilioToken() {
        return twilioToken;
    }

    public void setTwilioToken(String twilioToken) {
        this.twilioToken = twilioToken;
    }

    public String getTwilioUrl() {
        return twilioUrl;
    }

    public void setTwilioUrl(String twilioUrl) {
        this.twilioUrl = twilioUrl;
    }

    public String getFirebaseServerKey() {
        return firebaseServerKey;
    }

    public void setFirebaseServerKey(String firebaseServerKey) {
        this.firebaseServerKey = firebaseServerKey;
    }

    public String getFirebaseMessagingUrl() {
        return firebaseMessagingUrl;
    }

    public void setFirebaseMessagingUrl(String firebaseMessagingUrl) {
        this.firebaseMessagingUrl = firebaseMessagingUrl;
    }

    public String getFirebaseAppUrl() {
        return firebaseAppUrl;
    }

    public void setFirebaseAppUrl(String firebaseAppUrl) {
        this.firebaseAppUrl = firebaseAppUrl;
    }

    public String getFirebaseIconUrl() {
        return firebaseIconUrl;
    }

    public void setFirebaseIconUrl(String firebaseIconUrl) {
        this.firebaseIconUrl = firebaseIconUrl;
    }

    public void checkSendgridData() {
        if (StringUtils.isEmpty(emailSender)) {
            throw new SendgridProviderInfoNotConfiguredException("emailSender");
        }
        if (StringUtils.isEmpty(sendgridAppUrl)) {
            throw new SendgridProviderInfoNotConfiguredException("sendgridAppUrl");
        }
        if (StringUtils.isEmpty(sendgridApiKey)) {
            throw new SendgridProviderInfoNotConfiguredException("sendgridApiKey");
        }
        if (StringUtils.isEmpty(sendgridMailUrl)) {
            throw new SendgridProviderInfoNotConfiguredException("sendgridMailUrl");
        }
    }

    public void checkTwilioData() {
        if (StringUtils.isEmpty(twilioFromNumber)) {
            throw new TwilioProviderInfoNotConfiguredException("twilioFromNumber");
        }
        if (StringUtils.isEmpty(twilioSid)) {
            throw new TwilioProviderInfoNotConfiguredException("twilioSid");
        }
        if (StringUtils.isEmpty(twilioToken)) {
            throw new TwilioProviderInfoNotConfiguredException("twilioToken");
        }
        if (StringUtils.isEmpty(twilioUrl)) {
            throw new TwilioProviderInfoNotConfiguredException("twilioUrl");
        }
    }

    public void checkFirebaseData() {
        if (StringUtils.isEmpty(firebaseAppUrl)) {
            throw new FirebaseProviderInfoNotConfiguredException("firebaseAppUrl");
        }
        if (StringUtils.isEmpty(firebaseIconUrl)) {
            throw new FirebaseProviderInfoNotConfiguredException("firebaseIconUrl");
        }
        if (StringUtils.isEmpty(firebaseMessagingUrl)) {
            throw new FirebaseProviderInfoNotConfiguredException("firebaseMessagingUrl");
        }
        if (StringUtils.isEmpty(firebaseServerKey)) {
            throw new FirebaseProviderInfoNotConfiguredException("firebaseServerKey");
        }
    }
}
