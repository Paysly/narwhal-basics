package com.narwhal.basics.integrations.notifications.client.dto.settings;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.rest.model.BaseModelUpdatable;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentVariableDTO implements Serializable {

    private String id;
    private int sort;
    private String value;
    private Date createdAt;
    private Date updatedAt;

}
