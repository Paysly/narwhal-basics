package com.narwhal.basics.core.jobs.servlets;

import com.google.inject.Inject;
import com.narwhal.basics.core.jobs.services.JobStatusTrackerService;
import com.narwhal.basics.core.rest.servlets.TaskServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
public abstract class BaseTaskServlet extends HttpServlet implements TaskServlet {

    protected Logger logger;
    protected JobStatusTrackerService trackerService;

    @Inject
    public BaseTaskServlet(Logger logger, JobStatusTrackerService trackerService) {
        this.logger = logger;
        this.trackerService = trackerService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doTask(req, resp);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Unable to " + this.getClass().getSimpleName(), e);
        }
    }

    protected abstract void doTask(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException;
}
