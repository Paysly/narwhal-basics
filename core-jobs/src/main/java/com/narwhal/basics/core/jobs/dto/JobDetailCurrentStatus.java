package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomyair on 8/22/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetailCurrentStatus implements Serializable {
    private int currentLaunchedJobs;
    private int currentInProgressJobs;
    private int currentFailedJobs;
    private int currentCompletedJobs;
    private int totalJobs;
    private List<JobDetailCurrentStatusDay> days = new ArrayList<>();

    public void increaseJob(JobStatus jobStatus) {
        switch (jobStatus.getStatusType()) {
            case LAUNCHED:
                currentLaunchedJobs++;
                break;
            case IN_PROGRESS:
                currentInProgressJobs++;
                break;
            case FAILED:
                currentFailedJobs++;
                break;
            case COMPLETED:
                currentCompletedJobs++;
                break;
        }
        totalJobs++;
    }

    public void addJobToDay(JobStatus j) {
        JobDetailCurrentStatusDay day = new JobDetailCurrentStatusDay();
        //
        day.setDate(j.getCreatedAt());
        //
        day.increaseJob(j);
        //
        days.add(day);
    }
}
