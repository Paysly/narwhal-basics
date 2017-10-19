package com.narwhal.basics.core.jobs.dao;

import com.google.inject.ImplementedBy;
import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.rest.daos.BaseDao;
import com.narwhal.basics.core.rest.model.paging.PagingResult;

import java.util.List;

/**
 * Created by tomyair on 8/18/17.
 */
@ImplementedBy(JobStatusDaoObjectifyImpl.class)
public interface JobStatusDao extends BaseDao<JobStatus> {

    List<JobStatus> getRecentJobStatus();

    PagingResult<JobStatus> getPageOfSimilarJobStatus(JobType type, String name, int pageSize, String cursor);

    JobStatus getLastJobStatus(JobType jobType, String name);
}
