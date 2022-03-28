package edu.studio.c2c;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class RestClient {
    public static final String API_URI = "https://apiclasstocorporate.azurewebsites.net/api/users?userType=Student";

    public static String getUsers(String token) {
        HttpResponse<String> getHTTPResponse = Unirest.get(API_URI).header("Authorization", "Bearer " + token)
                .asString();
        System.out.println(getHTTPResponse.getStatus());
        if (isResponseSuccessful(getHTTPResponse)) {
            return " Hello" + getHTTPResponse.getBody();
        }
        else {
            return "Invalid";
        }
    }

    public static boolean isResponseSuccessful(HttpResponse<String> response) {
        if (response.getStatus() == 200) {
            return true;
        }
        else {
            return false;
        }
    }
}