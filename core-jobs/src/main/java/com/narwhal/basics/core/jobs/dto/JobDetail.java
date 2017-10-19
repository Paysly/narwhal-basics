package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;

import java.io.Serializable;

/**
 * Created by tomyair on 8/22/17.
 */
public class JobDetail implements Serializable {

    private JobStatus jobStatus;
    private JobDetailCurrentStatus currentStatuses;

    public JobDetail() {
    }

    public JobDetail(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public JobDetailCurrentStatus getCurrentStatuses() {
        return currentStatuses;
    }

    public void setCurrentStatuses(JobDetailCurrentStatus currentStatuses) {
        this.currentStatuses = currentStatuses;
    }
}
