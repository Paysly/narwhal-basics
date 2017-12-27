package com.narwhal.basics.core.exceptions.mapper;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.exceptions.dao.StoredExceptionDao;
import com.narwhal.basics.core.exceptions.model.StoredException;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

/**
 * @author Tomas de Priede
 */
@Provider
@Singleton
@Log
public class StoredApiExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    private StoredExceptionDao storedExceptionDao;

    @Override
    public Response toResponse(Exception exception) {
        //
        StoredException storedException = new StoredException();
        storedException.init(exception);
        //
        storedExceptionDao.save(storedException);
        //
        //
        if (exception instanceof ApiException) {
            //
            ApiException apiException = (ApiException) exception;
            log.log(Level.WARNING, apiException.getMessage(), apiException);
            return Response.status(apiException.code).entity(new ExceptionJson(apiException.code, apiException.getMessage())).build();
        } else {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            return Response.status(StoredException.SERVER_ERROR_CODE).entity(new ExceptionJson(StoredException.SERVER_ERROR_CODE, exception.getMessage())).build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ExceptionJson {

        private int code;
        private String message;
    }
}
