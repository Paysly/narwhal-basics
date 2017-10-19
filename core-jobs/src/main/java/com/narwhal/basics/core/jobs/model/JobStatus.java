package com.narwhal.basics.core.jobs.model;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.jobs.type.JobStatusType;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.jobs.utils.JobConstants;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tomyair on 8/19/17.
 */
@Entity
@Index
public class JobStatus implements BaseModel {

    @Id
    private String id;
    //
    @Unindex
    private JobStatusType statusType;
    //
    private JobType type;
    //
    private String name;

    @IgnoreSave(IfDefault.class)
    private String launchedBy = JobConstants.SYSTEM;
    //
    @Unindex
    private String exception;
    //
    @Unindex
    private Integer entitiesRead = 0;
    //
    @Unindex
    private Integer entitiesProcessed = 0;
    //
    private Date createdAt;
    @Unindex
    private Date updatedAt;

    public JobStatus() {
    }

    public void init() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public JobStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(JobStatusType statusType) {
        this.statusType = statusType;
    }

    public Integer getEntitiesRead() {
        return entitiesRead;
    }

    public void setEntitiesRead(Integer entitiesRead) {
        this.entitiesRead = entitiesRead;
    }

    public Integer getEntitiesProcessed() {
        return entitiesProcessed;
    }

    public void setEntitiesProcessed(Integer entitiesProcessed) {
        this.entitiesProcessed = entitiesProcessed;
    }

    public void increaseEntitiesRead(int entities) {
        this.entitiesRead += entities;
    }

    public void increaseEntitiesProcessed(int entities) {
        this.entitiesProcessed += entities;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaunchedBy() {
        return launchedBy;
    }

    public void setLaunchedBy(String launchedBy) {
        this.launchedBy = launchedBy;
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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
