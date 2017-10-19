package com.narwhal.basics.core.jobs.model;

import com.narwhal.basics.core.rest.model.paging.HasStringCursor;

/**
 * Created by tomyair on 8/19/17.
 */
public interface HasJobId extends HasStringCursor {
    String getJobId();
}
