package edu.studio.c2c;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UserParserTest {

    @Test
    public void testParseUsers() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);

        List<User> formattedUsers = parser.parseUsers(studentsSample);
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
