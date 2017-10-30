package com.narwhal.basics.external.core.model;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.rest.model.BaseModelUpdatable;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@Entity
@Index
public class EnvironmentVariable implements BaseModelUpdatable<EnvironmentVariableUpdatable> {

    @Id
    private String id;
    @Unindex
    private int sort;
    @Unindex
    @IgnoreSave(IfDefault.class)
    private String value;

    private Date createdAt;
    @Unindex
    private Date updatedAt;

    public void init(String id) {
        ApiPreconditions.checkNotNull(id, "id");
        //
        this.id = StringUtils.trim(StringUtils.lowerCase(id));
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    @Override
    public boolean requiresUpdate(EnvironmentVariableUpdatable dto) {
        return !StringUtils.equals(this.getValue(), dto.getValue())
                || sort != dto.getSort();
    }

    @Override
    public void update(EnvironmentVariableUpdatable dto) {
        this.setValue(dto.getValue());
        this.setSort(dto.getSort());
        this.updatedAt = new Date();
    }
}
