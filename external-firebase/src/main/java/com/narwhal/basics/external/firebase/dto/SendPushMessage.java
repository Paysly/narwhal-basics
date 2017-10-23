package com.narwhal.basics.external.firebase.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
public class SendPushMessage implements Serializable {

    private String to;
    private String title;
    private Map<String, Object> model;
    private String body;
    private String pushActionLink;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
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

    public String getPushActionLink() {
        return pushActionLink;
    }

    public void setPushActionLink(String pushActionLink) {
        this.pushActionLink = pushActionLink;
    }
}
