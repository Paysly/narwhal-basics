package com.narwhal.basics.core.jobs.services;


import com.narwhal.basics.core.jobs.dao.JobStatusDao;
import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.jobs.type.JobStatusType;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.jobs.utils.JobMemcachedKeys;
import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tomyair on 8/19/17.
 */
@Singleton
@Log
public class JobStatusTrackerService {

    @Inject
    private JobStatusDao jobStatusDao;
    @Inject
    private MemcachedService memcachedService;

    public JobStatus trackJobStarted(String adminUserEmail, JobType jobType, String jobName) {
        ApiPreconditions.checkNotNull(jobType, "jobType");
        ApiPreconditions.checkNotNull(jobName, "jobName");
        //
        JobStatus job = new JobStatus();
        job.init();
        //
        job.setStatusType(JobStatusType.LAUNCHED);
        job.setLaunchedBy(adminUserEmail);
        //
        job.setType(jobType);
        job.setName(jobName);
        //
        jobStatusDao.save(job);
        //
        memcachedService.put(JobMemcachedKeys.JOB_STATUS + job.getId(), job);
        //
        return job;
    }

    public void trackJobFinished(String jobId, int entitiesReadToAdd, int entitiesProcessedToAdd) {
        trackJobUpdate(JobStatusType.COMPLETED, jobId, entitiesReadToAdd, entitiesProcessedToAdd, null);
    }

    public void trackJobInProgress(String jobId, int readEntities, int processedEntities) {
        trackJobUpdate(JobStatusType.IN_PROGRESS, jobId, readEntities, processedEntities, null);
    }

    public void trackJobFailed(String jobId, int readEntities, int processedEntities, String exceptionTrace) {
        trackJobUpdate(JobStatusType.FAILED, jobId, readEntities, processedEntities, exceptionTrace);
    }

    public void trackJobFailed(String jobId, String exceptionTrace) {
        trackJobUpdate(JobStatusType.FAILED, jobId, 0, 0, exceptionTrace);
    }

    private void trackJobUpdate(JobStatusType statusType, String jobId, int readEntities, int processedEntities, String exceptionTrace) {
        ApiPreconditions.checkNotNull(jobId, "jobId");
        ApiPreconditions.checkNotNull(statusType, "statusType");
        //
        try {
            JobStatus jobStatus = jobStatusDao.get(JobStatus.class, jobId);
            //
            jobStatus.increaseEntitiesRead(readEntities);
            jobStatus.increaseEntitiesProcessed(processedEntities);
            //
            jobStatus.setException(exceptionTrace);
            //
            jobStatus.setStatusType(statusType);
            jobStatus.setUpdatedAt(new Date());
            //
            jobStatusDao.save(jobStatus);
            //
            memcachedService.put(JobMemcachedKeys.JOB_STATUS + jobId, jobStatus);
            //
        } catch (EntityNotFoundException e) {
            log.log(Level.SEVERE, "Failed to find job. Unable to track finish status with id: " + jobId, e);
        }
    }
}
