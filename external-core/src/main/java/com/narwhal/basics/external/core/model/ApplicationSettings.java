package com.narwhal.basics.external.core.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.exceptions.FirebaseProviderInfoNotConfiguredException;
import com.narwhal.basics.external.core.exceptions.SendgridProviderInfoNotConfiguredException;
import com.narwhal.basics.external.core.exceptions.TwilioProviderInfoNotConfiguredException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String firebaseIosKey;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String firebaseAndroidKey;
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
        if (StringUtils.isEmpty(firebaseIosKey) || StringUtils.isEmpty(firebaseAndroidKey)) {
            throw new FirebaseProviderInfoNotConfiguredException("firebase mobile key (ios or android)");
        }
    }
}
