package com.narwhal.basics.external.firebase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tomyair on 7/14/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseCloudMessageResponse {
    @JsonProperty("multicast_id")
    private String multicastId;
    private Long success;
    private Long failure;
    @JsonProperty("canonical_ids")
    private Long canonicalIds;

}
