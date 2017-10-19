package com.narwhal.basics.core.rest.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.narwhal.basics.core.rest.exceptions.ApplicationException;
import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;
import com.narwhal.basics.core.rest.exceptions.api.UnauthorizedException;
import com.narwhal.basics.core.rest.utils.WebConstants;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
public class JWTTokenUtils {

    public static String createToken(String id, String secret) {
        return createToken(id, secret, null, null);
    }

    public static String createToken(String id, String secret, Date expiresAt, Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            //
            JWTCreator.Builder builder = JWT.create().withIssuer(WebConstants.APP_NAME);
            //
            if (claims != null) {
                for (Map.Entry<String, String> claim : claims.entrySet()) {
                    builder = builder.withClaim(claim.getKey(), claim.getValue());
                }
            }
            if (expiresAt != null) {
                builder = builder.withExpiresAt(expiresAt);
            }
            //
            builder = builder.withIssuedAt(new Date()).withKeyId(id);
            //
            String token = builder.sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException e) {
            throw new ApplicationException("Failed to create JWT Tokens");
        }
    }

    public static DecodedTokenCredentials decodeToken(String token, String secret) {
        if (token == null) {
            throw new UnauthorizedException("Invalid JWT User Token");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(WebConstants.APP_NAME).build();
            DecodedJWT jwt = verifier.verify(token);
            return new DecodedTokenCredentials(jwt.getKeyId(), jwt.getIssuedAt(), jwt.getExpiresAt(), jwt.getClaims());
        } catch (UnsupportedEncodingException | JWTDecodeException e) {
            throw new BadRequestException("Failed to create JWT Tokens");
        }
    }
}
