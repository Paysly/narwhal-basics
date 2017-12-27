package com.narwhal.basics.external.firebase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by tomyair on 7/14/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebasePayload implements Serializable {
    private String title;
    private String body;
    private String clickAction;
    private Map<String, Object> data;
    private String icon;

    public FirebaseCloudMessageNotification getNotification() {
        return new FirebaseCloudMessageNotification(title, body, icon, clickAction);
    }
}
