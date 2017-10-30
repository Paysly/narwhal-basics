package com.narwhal.basics.integrations.notifications.client.dto.settings;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.rest.model.BaseModelUpdatable;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class EnvironmentVariableDTO implements Serializable {

    private String id;
    private int sort;
    private String value;
    private Date createdAt;
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
