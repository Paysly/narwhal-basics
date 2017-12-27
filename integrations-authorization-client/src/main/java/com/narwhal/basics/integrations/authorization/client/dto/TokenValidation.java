package com.narwhal.basics.integrations.authorization.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidation implements Serializable {
    private String jwt;
}
