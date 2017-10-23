package com.narwhal.basics.external.firebase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tomyair on 7/14/17.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseCloudMessageNotification {
    private String title;
    private String body;
    private String icon;
    @JsonProperty("click_action")
    private String clickAction;

    public FirebaseCloudMessageNotification(String title, String body, String icon, String clickAction) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.clickAction = clickAction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }
}
