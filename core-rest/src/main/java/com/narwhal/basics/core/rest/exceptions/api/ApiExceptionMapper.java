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
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {
    private final static Logger logger = Logger.getLogger(ApiExceptionMapper.class.getSimpleName());

    @Override
    public Response toResponse(ApiException apiException) {
        if (apiException.code >= 500) {
            logger.log(Level.SEVERE, "Server Exception: " + apiException.getMessage(), apiException);
        } else {
            logger.log(Level.INFO, "Client Exception: " + apiException.getMessage(), apiException);
        }
        return Response.status(apiException.code).entity(new ExceptionJson(apiException.code, apiException.getMessage())).build();
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
