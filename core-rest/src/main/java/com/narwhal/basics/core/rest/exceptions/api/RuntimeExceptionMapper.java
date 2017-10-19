package com.narwhal.basics.core.rest.exceptions.api;

import com.google.inject.Singleton;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
@Provider
@Singleton
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static Logger logger = Logger.getLogger(RuntimeExceptionMapper.class.getSimpleName());

    @Override
    public Response toResponse(RuntimeException runtimeException) {
        logger.log(Level.SEVERE, runtimeException.getMessage(), runtimeException);
        return Response.status(500).entity(runtimeException.getMessage()).build();
    }
}
