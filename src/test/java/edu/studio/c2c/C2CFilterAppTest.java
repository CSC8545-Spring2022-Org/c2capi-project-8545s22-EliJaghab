package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class C2CFilterAppTest {

    @Test
    public void testValidateBearerToken() {
        C2CFilterApp app = new C2CFilterApp();

        String[] nullBearerToken = { "" };
        Boolean nullResponse = app.validateBearerToken(nullBearerToken);
        assertFalse(nullResponse);

        String[] nonStringBearerToken = { "234" };
        Boolean nonStringResponse = app.validateBearerToken(nonStringBearerToken);
        assertFalse(nonStringResponse);

        String[] moreThanOneBearerToken = { "eyJhbGciOiJSUzI1", "eyJhbGciOiJSUzI1" };
        Boolean moreThanOneResponse = app.validateBearerToken(moreThanOneBearerToken);
        assertFalse(moreThanOneResponse);

        String[] validBearerToken = { "eyJhbGciOiJSUzI1" };
        Boolean validResponse = app.validateBearerToken(validBearerToken);
        assertTrue(validResponse);

    }

}
