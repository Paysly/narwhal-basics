package com.narwhal.basics.external.sendgrid.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
public class SendEmailMessage implements Serializable {

    private String to;
    private String subject;
    private Map<String, Object> model;
    private String htmlTemplate;
    private String plainTemplate;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

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

    public String getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }

    public String getPlainTemplate() {
        return plainTemplate;
    }

    public void setPlainTemplate(String plainTemplate) {
        this.plainTemplate = plainTemplate;
    }
}
