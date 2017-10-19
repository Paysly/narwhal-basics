package com.narwhal.basics.core.rest.model;

import java.io.Serializable;
import java.util.Date;

public interface BaseModel extends Serializable {

    /**
     * @return the unique id associated with the entity
     */
    public String getId();

    /**
     * Caution! This method should only be used when necessary. Avoid modifying
     * the id programatically whenever possible since this introduces the
     * potential for id mismatches, which can lead to other problems.
     *
     * @param id the unique id associated with the entity.
     */
    public void setId(String id);

    public Date getCreatedAt();

    public void setCreatedAt(Date createdAt);
}
