package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class RestClientTest {

    @Test
    public void testGetUsers() {
        RestClient client = new RestClient();
        String token = obtainBearerTokenUsingSystemProps();
        String actualResponse = client.getUsers(token);
        assertNotNull(actualResponse);
        System.out.println(actualResponse);
    }

    protected String obtainBearerTokenUsingSystemProps() {
        String token = System.getProperty("bearer.token");
        return token;
    }
}