package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RestClientTest {

    @Test
    public void testValidGetUsers() {
        RestClient client = new RestClient();
        String token = obtainBearerTokenUsingSystemProps();
        String actualResponse = client.getUsers(token);
        assertNotNull(actualResponse);
    }

    @Test
    public void testInvalidGetUsers() {
        RestClient client = new RestClient();
        String token = "invalid_token";
        String actualResponse = client.getUsers(token);
        String expectedResponse = "Invalid Response: Error";
        assertTrue(actualResponse.contains(expectedResponse));
    }

    protected String obtainBearerTokenUsingSystemProps() {
        String token = System.getProperty("bearer.token");
        return token;
    }
}