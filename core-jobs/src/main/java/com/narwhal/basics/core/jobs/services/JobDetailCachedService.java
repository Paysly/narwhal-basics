package com.narwhal.basics.core.jobs.services;


import com.narwhal.basics.core.jobs.dto.JobDetailCurrentStatus;
import com.narwhal.basics.core.jobs.dto.JobNames;
import com.narwhal.basics.core.jobs.dto.JobStatuses;
import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.jobs.utils.JobMemcachedKeys;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tomyair on 8/22/17.
 */
@Singleton
public class JobDetailCachedService {

    @Inject
    private JobDetailService jobDetailService;
    @Inject
    private MemcachedService memcachedService;

    public JobStatus getJobStatus(String jobId) {
        ApiPreconditions.checkNotNull(jobId, "jobId");
        JobStatus status = (JobStatus) memcachedService.get(JobMemcachedKeys.JOB_STATUS + jobId);
        //
        if (status != null) {
            return status;
        } else {
            status = jobDetailService.getJobStatus(jobId);
            memcachedService.put(JobMemcachedKeys.JOB_STATUS + jobId, status);
            return status;
        }
    }

    public JobDetailCurrentStatus getCurrentStatusOfLastSimilarJobs(JobStatus jobStatus) {
        ApiPreconditions.checkNotNull(jobStatus, "jobStatus");
        return jobDetailService.getCurrentStatusOfLastSimilarJobs(jobStatus);
    }

    public JobStatuses getLastJobOfType(JobType jobType, JobNames names) {
        return jobDetailService.getLastJobOfType(jobType, names);
    }
}
