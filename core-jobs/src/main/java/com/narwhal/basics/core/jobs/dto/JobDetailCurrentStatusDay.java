package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tomyair on 8/22/17.
 */
public class JobDetailCurrentStatusDay implements Serializable {
    private int read;
    private int processed;
    private Date date;

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void increaseJob(JobStatus jobStatus) {
        read += jobStatus.getEntitiesRead();
        processed += jobStatus.getEntitiesProcessed();
    }
}
