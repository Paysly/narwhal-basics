package com.narwhal.basics.core.exceptions.model;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.model.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Index
public class StoredException implements BaseModel {

    public static final int SERVER_ERROR_CODE = 500;
    @Id
    private String id;

    private int code;

    private String exceptionName;

    private Set<String> searchNames;

    @Unindex
    private String message;

    @Unindex
    private String stackTrace;

    private Date createdAt;

    @Unindex
    private Date updatedAt;

    public void init(Exception exception) {
        this.id = UUID.randomUUID().toString();
        this.exceptionName = exception.getClass().getName();
        //
        if (exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            this.code = apiException.getCode();
        } else {
            this.code = SERVER_ERROR_CODE;
        }
        //
        this.stackTrace = ExceptionUtils.getFullStackTrace(exception);
        this.message = exception.getMessage();
        //
        this.searchNames = new HashSet<>();
        this.searchNames.add(StringUtils.lowerCase(this.exceptionName));
        this.searchNames.add(StringUtils.lowerCase(exception.getClass().getSimpleName()));
        //
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }
}
