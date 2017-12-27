package com.narwhal.basics.core.jobs.model;

import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfDefault;
import com.narwhal.basics.core.jobs.type.JobStatusType;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.jobs.utils.JobConstants;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tomyair on 8/19/17.
 */
@Data
@NoArgsConstructor
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

    public void init() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }


    public void increaseEntitiesRead(int entities) {
        this.entitiesRead += entities;
    }

    public void increaseEntitiesProcessed(int entities) {
        this.entitiesProcessed += entities;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
