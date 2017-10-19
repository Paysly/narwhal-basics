package com.narwhal.basics.core.jobs.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.jobs.dao.JobStatusDao;
import com.narwhal.basics.core.jobs.dto.JobDetail;
import com.narwhal.basics.core.jobs.dto.JobNames;
import com.narwhal.basics.core.jobs.dto.JobStatuses;
import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.jobs.services.JobDetailCachedService;
import com.narwhal.basics.core.jobs.type.JobType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
@Singleton
@Path("/v1/jobs/")
public class JobStatusApi {

    private Logger logger = Logger.getLogger(JobStatusApi.class.getSimpleName());

    @Inject
    private JobStatusDao jobStatusDao;
    @Inject
    private JobDetailCachedService jobDetailCachedService;

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recentJobStatuses(
            @HeaderParam("Auth") String authHeader) {
        //
        logger.log(Level.INFO, "Getting recent job statuses");
        //
        List<JobStatus> jobStatus = jobStatusDao.getRecentJobStatus();
        //
        return Response.ok(jobStatus).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobDetail(
            @HeaderParam("Auth") String authHeader, @PathParam("id") String jobId) {
        //
        logger.log(Level.INFO, "Getting job detail");
        //
        JobDetail jobDetail = new JobDetail();
        //
        jobDetail.setJobStatus(jobDetailCachedService.getJobStatus(jobId));
        //
        jobDetail.setCurrentStatuses(jobDetailCachedService.getCurrentStatusOfLastSimilarJobs(jobDetail.getJobStatus()));
        //
        return Response.ok(jobDetail).build();
    }

    @POST
    @Path("/status/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response lastJobStatus(
            @HeaderParam("Auth") String authHeader, @PathParam("type") JobType type, JobNames jobNames) {
        //
        logger.log(Level.INFO, "Getting last job runs for type: " + type.toString());
        //
        JobStatuses jobs = jobDetailCachedService.getLastJobOfType(type, jobNames);
        //
        return Response.ok(jobs).build();
    }
}
