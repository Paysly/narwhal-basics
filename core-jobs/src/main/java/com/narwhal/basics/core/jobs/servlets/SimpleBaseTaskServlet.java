package com.narwhal.basics.core.jobs.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.narwhal.basics.core.jobs.model.HasJobId;
import com.narwhal.basics.core.jobs.model.JobSimpleResult;
import com.narwhal.basics.core.jobs.services.JobStatusTrackerService;
import com.narwhal.basics.core.rest.utils.ObjectSerializationUtils;

/**
 * @author Tomas de Priede
 */
public abstract class SimpleBaseTaskServlet<T extends HasJobId> extends BaseTaskServlet {

    @Inject
    public SimpleBaseTaskServlet(Logger logger, JobStatusTrackerService trackerService) {
        super(logger, trackerService);
    }

    protected abstract String dtoLogName(T dto);

    protected abstract JobSimpleResult doSimpleTask(T dto);

    protected void doTask(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {
        T dto = (T) ObjectSerializationUtils.deserialize(ObjectSerializationUtils.getBytes(req, logger));
        JobSimpleResult result = null;
        try {
            logger.log(Level.INFO, String.format("Starting %s task for %s", this.getClass().getSimpleName(), dtoLogName(dto)));
            //
            result = doSimpleTask(dto);
            //
            onFinish(dto);
            trackerService.trackJobFinished(dto.getJobId(), result.getReadEntities(), result.getProcessedEntities());
            logger.log(Level.INFO, String.format("Finished %s class task for %s", this.getClass().getSimpleName(), dtoLogName(dto)));
        } catch (Exception e) {
            String exceptionTrace = ExceptionUtils.getStackTrace(e);
            if (result != null) {
                trackerService.trackJobFailed(dto.getJobId(), result.getReadEntities(), result.getProcessedEntities(), exceptionTrace);
            } else {
                trackerService.trackJobFailed(dto.getJobId(), exceptionTrace);
            }
            logger.log(Level.INFO, String.format("Failed task for %s", this.getClass().getSimpleName(), dtoLogName(dto)));
        }
    }

    protected abstract void onFinish(T dto);
}
