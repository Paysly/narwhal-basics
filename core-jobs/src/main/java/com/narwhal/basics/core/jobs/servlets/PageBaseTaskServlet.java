package com.narwhal.basics.core.jobs.servlets;

import com.google.inject.Inject;
import com.narwhal.basics.core.jobs.model.HasJobId;
import com.narwhal.basics.core.jobs.model.JobPagingResult;
import com.narwhal.basics.core.jobs.services.JobStatusTrackerService;
import com.narwhal.basics.core.rest.servlets.TaskLauncher;
import com.narwhal.basics.core.rest.utils.ObjectSerializationUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
public abstract class PageBaseTaskServlet<T extends HasJobId> extends BaseTaskServlet {

    private TaskLauncher taskLauncher;


    @Inject
    public PageBaseTaskServlet(Logger logger, TaskLauncher taskLauncher, JobStatusTrackerService trackerService) {
        super(logger, trackerService);
        this.taskLauncher = taskLauncher;
    }

    protected abstract String dtoLogName(T dto);

    protected abstract JobPagingResult doPagingTask(T dto);

    protected abstract String getQueue();

    protected abstract T newDto(T oldDto, String newCursor);

    protected void doTask(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {
        T dto = (T) ObjectSerializationUtils.deserialize(ObjectSerializationUtils.getBytes(req, logger));
        JobPagingResult result = null;
        try {
            //
            if (dto.getCursor() != null) {
                logger.log(Level.INFO, String.format("Resuming %s class task with cursor: %s for %s", this.getClass().getSimpleName(), dto.getCursor(), dtoLogName(dto)));
            } else {
                logger.log(Level.INFO, String.format("Starting %s task for %s", this.getClass().getSimpleName(), dtoLogName(dto)));
            }
            //
            result = doPagingTask(dto);
            //
            // Continue paging
            if (result.getCursor() != null) {
                trackerService.trackJobInProgress(dto.getJobId(), result.getReadEntities(), result.getProcessedEntities());
                logger.log(Level.INFO, String.format("Launching new job %s class task for %s", this.getClass().getSimpleName(), dtoLogName(dto)));
                dto = newDto(dto, result.getCursor());
                taskLauncher.launchTask(this.getClass(), ObjectSerializationUtils.serialize(dto), getQueue());
            } else {
                onFinish(dto);
                trackerService.trackJobFinished(dto.getJobId(), result.getReadEntities(), result.getProcessedEntities());
                logger.log(Level.INFO, String.format("Finished %s class task for %s", this.getClass().getSimpleName(), dtoLogName(dto)));
            }
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
