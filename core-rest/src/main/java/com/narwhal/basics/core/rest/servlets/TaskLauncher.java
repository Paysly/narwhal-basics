package com.narwhal.basics.core.rest.servlets;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.guice.URLPaths;

/**
 * User: Tomas de Priede Date: 6/1/14 Time: 9:25 PM
 */
@Singleton
public class TaskLauncher {

    public static final String APPLICATION_X_JAVA_SERIALIZED_OBJECT = "application/x-java-serialized-object";

    public void launchTask(Class<? extends TaskServlet> task, TaskParameter... taskParameters) {
        Queue q = QueueFactory.getDefaultQueue();
        TaskOptions taskOptions = TaskOptions.Builder.withUrl(URLPaths.findTerminatedPath(task));
        //
        if (taskParameters != null) {
            for (TaskParameter p : taskParameters) {
                taskOptions.param(p.getName(), p.getValue());
            }
        }
        //
        taskOptions.countdownMillis(1000).method(TaskOptions.Method.POST);
        q.add(taskOptions);
    }

    public void launchTask(Class<? extends TaskServlet> task, byte[] serialized) {
        launchTask(task, serialized, null);
    }

    public void launchTask(Class<? extends TaskServlet> task, byte[] serialized, String queue) {
        Queue q;
        if (queue == null) {
            q = QueueFactory.getDefaultQueue();
        } else {
            q = QueueFactory.getQueue(queue);
        }
        TaskOptions taskOptions = TaskOptions.Builder.withUrl(URLPaths.findTerminatedPath(task));
        taskOptions.method(TaskOptions.Method.POST);
        taskOptions.payload(serialized, APPLICATION_X_JAVA_SERIALIZED_OBJECT);
        q.add(taskOptions);
    }
}