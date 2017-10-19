package com.narwhal.basics.core.jobs.services;

import com.narwhal.basics.core.jobs.dao.JobStatusDao;
import com.narwhal.basics.core.jobs.dto.JobDetailCurrentStatus;
import com.narwhal.basics.core.jobs.dto.JobNames;
import com.narwhal.basics.core.jobs.dto.JobStatuses;
import com.narwhal.basics.core.jobs.exceptions.JobDetailNotFoundException;
import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.model.paging.PagingResult;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tomyair on 8/22/17.
 */
@Singleton
public class JobDetailService {

    public static final int LAST_THIRTY_JOBS = 30;
    @Inject
    private JobStatusDao jobStatusDao;

    public JobStatus getJobStatus(String jobId) {
        ApiPreconditions.checkNotNull(jobId, "jobId");
        //
        try {
            JobStatus status = jobStatusDao.get(JobStatus.class, jobId);
            return status;
        } catch (EntityNotFoundException e) {
            throw new JobDetailNotFoundException(jobId, e);
        }
    }

    public JobDetailCurrentStatus getCurrentStatusOfLastSimilarJobs(JobStatus jobStatus) {
        ApiPreconditions.checkNotNull(jobStatus, "jobStatus");
        //
        JobDetailCurrentStatus detailCurrentStatus = new JobDetailCurrentStatus();
        //
        PagingResult<JobStatus> pagingResult = jobStatusDao.getPageOfSimilarJobStatus(jobStatus.getType(), jobStatus.getName(), LAST_THIRTY_JOBS, null);
        //
        for (JobStatus j : pagingResult.getResultList()) {
            detailCurrentStatus.increaseJob(j);
            //
            detailCurrentStatus.addJobToDay(j);
        }
        //
        return detailCurrentStatus;
    }

    public JobStatuses getLastJobOfType(JobType jobType, JobNames names) {
        ApiPreconditions.checkNotNull(jobType, "jobType");
        ApiPreconditions.checkNotNull(names, "names");
        //
        JobStatuses jobStatuses = new JobStatuses();
        //
        for (String name : names.getNames()) {
            JobStatus jobStatus = jobStatusDao.getLastJobStatus(jobType, name);
            jobStatuses.getList().add(jobStatus);
        }
        //
        return jobStatuses;
    }
}
