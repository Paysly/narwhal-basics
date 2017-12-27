package com.narwhal.basics.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by tomyair on 8/18/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringDto implements Serializable {

    private String value;
}
