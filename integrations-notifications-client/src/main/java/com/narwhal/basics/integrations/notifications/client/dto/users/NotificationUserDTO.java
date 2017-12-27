package com.narwhal.basics.integrations.notifications.client.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationUserDTO implements Serializable {

    private String id; // random unique id
    //
    private String email;
    //
    private String phone;
    //
    private String firebaseToken;
    //
    private Boolean emailVerified;
    //
    private Boolean phoneVerified;
    //
    private Boolean suspended;
    //
    private Date createdAt;
    //
    private Date updatedAt;

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
