package com.narwhal.basics.external.firebase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tomyair on 7/14/17.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseCloudMessageResponse {
    @JsonProperty("multicast_id")
    private String multicastId;
    private Long success;
    private Long failure;
    @JsonProperty("canonical_ids")
    private Long canonicalIds;

    public String getMulticastId() {
        return multicastId;
    }

    public void setMulticastId(String multicastId) {
        this.multicastId = multicastId;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getFailure() {
        return failure;
    }

    public void setFailure(Long failure) {
        this.failure = failure;
    }

    public Long getCanonicalIds() {
        return canonicalIds;
    }

    public void setCanonicalIds(Long canonicalIds) {
        this.canonicalIds = canonicalIds;
    }
}
