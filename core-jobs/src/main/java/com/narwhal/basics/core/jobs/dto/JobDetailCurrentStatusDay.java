package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tomyair on 8/22/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetailCurrentStatusDay implements Serializable {
    private int read;
    private int processed;
    private Date date;

    public void increaseJob(JobStatus jobStatus) {
        read += jobStatus.getEntitiesRead();
        processed += jobStatus.getEntitiesProcessed();
    }
}
