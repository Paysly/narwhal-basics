package com.narwhal.basics.core.rest.utils.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.narwhal.basics.core.rest.utils.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Tomas de Priede
 */
public class JWTTokenUtilsTest {

    @Test
    public void test_jwtToken_validSecret() {
        String id = "mail@google.com";
        String secret = "mysupersecret";
        //
        Date expiresAt = DateUtils.increaseOneDay(DateUtils.todayNoTime());
        //
        String token = JWTTokenUtils.createToken(id, secret, expiresAt, null);
        assertNotNull(token);
        //
        DecodedTokenCredentials tokenCredentials = JWTTokenUtils.decodeToken(token, secret);
        assertNotNull(tokenCredentials);
        //
        assertEquals(id, tokenCredentials.getId());
    }

    @Test
    public void test_jwtToken_validSecret_withClaims() {
        String id = "mail@google.com";
        String secret = "mysupersecret";
        //
        Map<String, String> claims = new HashMap<>();
        claims.put("project-id", "paysly");
        claims.put("action", "preview");
        //
        String token = JWTTokenUtils.createToken(id, secret, null, claims);
        assertNotNull(token);
        //
        DecodedTokenCredentials tokenCredentials = JWTTokenUtils.decodeToken(token, secret);
        assertNotNull(tokenCredentials);
        //
        assertEquals(id, tokenCredentials.getId());
        assertEquals("paysly", tokenCredentials.getClaims().get("project-id"));
        assertEquals("preview", tokenCredentials.getClaims().get("action"));
    }

    @Test(expected = TokenExpiredException.class)
    public void test_jwtToken_validSecret_invalidExpiration() {
        String id = "mail@google.com";
        String secret = "mysupersecret";
        //
        Map<String, String> claims = new HashMap<>();
        claims.put("project-id", "paysly");
        claims.put("action", "preview");
        //
        Date expiresAt = DateUtils.yesterdayNoTime();
        //
        String token = JWTTokenUtils.createToken(id, secret, expiresAt, claims);
        assertNotNull(token);
        //
        JWTTokenUtils.decodeToken(token, secret);
    }

    @Test(expected = SignatureVerificationException.class)
    public void test_jwtToken_invalidSecret() {
        String id = "mail@google.com";
        String secret = "mysupersecret";
        //
        String token = JWTTokenUtils.createToken(id, secret);
        assertNotNull(token);
        //
        JWTTokenUtils.decodeToken(token, "bad_secret");
    }
}
