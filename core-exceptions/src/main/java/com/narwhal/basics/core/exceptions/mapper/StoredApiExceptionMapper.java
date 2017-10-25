package com.narwhal.basics.core.exceptions.mapper;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.exceptions.dao.StoredExceptionDao;
import com.narwhal.basics.core.exceptions.model.StoredException;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Tomas de Priede
 */
@Provider
@Singleton
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
        if (exception instanceof ApiException) {
            //
            ApiException apiException = (ApiException) exception;
            return Response.status(apiException.code).entity(new ExceptionJson(apiException.code, apiException.getMessage())).build();
        } else {
            return Response.status(StoredException.SERVER_ERROR_CODE).entity(new ExceptionJson(StoredException.SERVER_ERROR_CODE, exception.getMessage())).build();
        }
    }

    public class ExceptionJson {

        private int code;
        private String message;

        public ExceptionJson() {
        }

        public ExceptionJson(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
