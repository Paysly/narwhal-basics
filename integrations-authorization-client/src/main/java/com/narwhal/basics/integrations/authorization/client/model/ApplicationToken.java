package com.narwhal.basics.integrations.authorization.client.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;

import java.util.Date;

@Entity
@Index
public class ApplicationToken implements BaseModel {

    /**
     * scopes splited by pipe. With that if I add a new scope I will automatically generate a new token
     */
    @Id
    private String id;

    @Unindex
    private String token;
    @Unindex
    private long expiration;
    //
    @Unindex
    private Date createdAt;

    public void init(String id, String token, long expiration) {
        ApiPreconditions.checkNotNull(id, "id");
        ApiPreconditions.checkNotNull(token, "token");
        this.id = id;
        this.token = token;
        this.expiration = expiration;
        //
        this.createdAt = new Date();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpirationDate() {
        return new Date(expiration);
    }
}
