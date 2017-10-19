package com.narwhal.basics.core.rest.utils.jwt;

import com.auth0.jwt.interfaces.Claim;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
public class DecodedTokenCredentials implements Serializable {

    private String id;
    private Date issuedAt;
    private Date expiresAt;
    private Map<String, String> claims;

    public DecodedTokenCredentials(String id, Date issuedAt, Date expiresAt, Map<String, Claim> claims) {
        this.id = id;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        //
        if (claims != null) {
            this.claims = new HashMap<>();
            for (Map.Entry<String, Claim> c : claims.entrySet()) {
                this.claims.put(c.getKey(), c.getValue().asString());
            }
        }
    }

    public String getId() {
        return id;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public Map<String, String> getClaims() {
        return claims;
    }
}
