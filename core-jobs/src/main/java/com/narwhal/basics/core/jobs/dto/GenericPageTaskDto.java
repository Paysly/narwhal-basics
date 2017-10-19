package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.HasJobId;

import java.io.Serializable;

/**
 * Created by tomyair on 8/18/17.
 */
public class GenericPageTaskDto implements Serializable, HasJobId {

    private String jobId;
    private String clazzName;
    private String cursor;

    public GenericPageTaskDto() {
    }

    public GenericPageTaskDto(String jobId, String clazzName, String cursor) {
        this.jobId = jobId;
        this.clazzName = clazzName;
        this.cursor = cursor;
    }

    @Override
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
