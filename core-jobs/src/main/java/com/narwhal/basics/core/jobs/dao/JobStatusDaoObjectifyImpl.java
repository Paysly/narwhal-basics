package com.narwhal.basics.core.jobs.dao;

import com.googlecode.objectify.cmd.Query;
import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.jobs.type.JobType;
import com.narwhal.basics.core.rest.daos.BaseDaoObjectifyImpl;
import com.narwhal.basics.core.rest.daos.OfyService;
import com.narwhal.basics.core.rest.model.paging.PagingResult;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by tomyair on 8/18/17.
 */
@Singleton
public class JobStatusDaoObjectifyImpl extends BaseDaoObjectifyImpl<JobStatus> implements JobStatusDao {
    private static final Integer DEFAULT_PAGE_SIZE = 100;

    @Override
    public List<JobStatus> getRecentJobStatus() {
        Query query = OfyService.ofy().load().type(JobStatus.class);
        query = query.order("-createdAt");
        return getPagingResult(query, DEFAULT_PAGE_SIZE, null).getResultList();
    }

    @Override
    public PagingResult<JobStatus> getPageOfSimilarJobStatus(JobType type, String name, int pageSize, String cursor) {
        Query query = OfyService.ofy().load().type(JobStatus.class);
        query = query.filter("type", type);
        query = query.filter("name", name);
        query = query.order("-createdAt");
        return getPagingResult(query, pageSize, cursor);
    }

    @Override
    public JobStatus getLastJobStatus(JobType type, String name) {
        Query query = OfyService.ofy().load().type(JobStatus.class);
        query = query.filter("type", type);
        query = query.filter("name", name);
        query = query.order("-createdAt");
        return (JobStatus) query.first().now();
    }
}
