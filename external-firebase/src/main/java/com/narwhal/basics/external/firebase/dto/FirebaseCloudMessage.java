package com.narwhal.basics.external.firebase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by tomyair on 7/14/17.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseCloudMessage implements Serializable {

    private String to;
    private FirebaseCloudMessageNotification notification;
    private Map<String, Object> data;

    public FirebaseCloudMessage() {
    }

    public FirebaseCloudMessage(String to, FirebaseCloudMessageNotification notification, Map<String, Object> data) {
        this.to = to;
        this.notification = notification;
        this.data = data;
    }

    public FirebaseCloudMessageNotification getNotification() {
        return notification;
    }

    public void setNotification(FirebaseCloudMessageNotification notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
