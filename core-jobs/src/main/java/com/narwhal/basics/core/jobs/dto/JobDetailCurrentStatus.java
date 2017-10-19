package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomyair on 8/22/17.
 */
public class JobDetailCurrentStatus implements Serializable {
    private int currentLaunchedJobs;
    private int currentInProgressJobs;
    private int currentFailedJobs;
    private int currentCompletedJobs;
    private int totalJobs;
    private List<JobDetailCurrentStatusDay> days = new ArrayList<>();

    public int getCurrentLaunchedJobs() {
        return currentLaunchedJobs;
    }

    public void setCurrentLaunchedJobs(int currentLaunchedJobs) {
        this.currentLaunchedJobs = currentLaunchedJobs;
    }

    public int getCurrentInProgressJobs() {
        return currentInProgressJobs;
    }

    public void setCurrentInProgressJobs(int currentInProgressJobs) {
        this.currentInProgressJobs = currentInProgressJobs;
    }

    public int getCurrentFailedJobs() {
        return currentFailedJobs;
    }

    public void setCurrentFailedJobs(int currentFailedJobs) {
        this.currentFailedJobs = currentFailedJobs;
    }

    public int getCurrentCompletedJobs() {
        return currentCompletedJobs;
    }

    public void setCurrentCompletedJobs(int currentCompletedJobs) {
        this.currentCompletedJobs = currentCompletedJobs;
    }

    public int getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(int totalJobs) {
        this.totalJobs = totalJobs;
    }

    public List<JobDetailCurrentStatusDay> getDays() {
        return days;
    }

    public void setDays(List<JobDetailCurrentStatusDay> days) {
        this.days = days;
    }

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
