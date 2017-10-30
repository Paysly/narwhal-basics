package com.narwhal.basics.external.core.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.narwhal.basics.external.core.model.EnvironmentVariableUpdatable;

import java.util.Date;

@Entity
@Index
public class EnvironmentVariableDTO implements EnvironmentVariableUpdatable, Comparable<EnvironmentVariableDTO> {

    private String id;
    private int sort;
    private String value;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
    public int compareTo(EnvironmentVariableDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
