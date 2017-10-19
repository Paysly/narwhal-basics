package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomyair on 8/22/17.
 */
public class JobStatuses implements Serializable {

    private List<JobStatus> list = new ArrayList<>();

    public JobStatuses() {
    }

    public List<JobStatus> getList() {
        return list;
    }

    public void setList(List<JobStatus> list) {
        this.list = list;
    }
}
