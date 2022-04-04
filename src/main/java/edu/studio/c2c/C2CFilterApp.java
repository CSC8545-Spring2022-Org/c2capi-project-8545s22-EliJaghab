package edu.studio.c2c;

import java.util.List;

public class C2CFilterApp {
    protected static final String ONLY_INT_BEARER_TOKEN = "Error: Bearer Token Invalid";
    protected static final String NULL_BEARER_TOKEN = "Error: Bearer Token Null";
    protected static final String MORE_THAN_ONE_BEARER_TOKEN = "Error: Bearer Token Formatted Incorrectly";

    public static void main(String[] args) {
        if (validateBearerToken(args)) {
            String rawStudents = getRawStudents(args[0]);
            UserParser parser = new UserParser();
            List<User> formattedStudents = parser.parseUsers(rawStudents);
            StudentFilter studentFilter = new StudentFilter();
            studentFilter.startConsole(formattedStudents);
        }
    }

    public static boolean validateBearerToken(String[] args) {
        boolean validToken = false;
        if (args.length == 0 || args[0].isEmpty()) {
            System.out.println(NULL_BEARER_TOKEN);
            validToken = false;
        }
        else if (args.length > 1) {
            System.out.println(MORE_THAN_ONE_BEARER_TOKEN);
            validToken = false;
        }
        else if (args[0].matches("[0-9]+")) {
            System.out.println(ONLY_INT_BEARER_TOKEN);
            validToken = false;
        }
        else {
            validToken = true;
        }
        return validToken;
    }

    public static String getRawStudents(String token) {
        RestClient client = new RestClient();
        return RestClient.getUsers(token);
    }
}
