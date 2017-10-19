package com.narwhal.basics.core.jobs.exceptions;


import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.exceptions.api.NotFoundException;

/**
 * @author Tomas de Priede
 */
public class JobDetailNotFoundException extends NotFoundException {

    public static final String MESSAGE = "Job detail not found with id: ";

    public JobDetailNotFoundException(String jobId) {
        super(MESSAGE + jobId);
    }

    public JobDetailNotFoundException(String jobId, EntityNotFoundException e) {
        super(MESSAGE + jobId, e);
    }
}
