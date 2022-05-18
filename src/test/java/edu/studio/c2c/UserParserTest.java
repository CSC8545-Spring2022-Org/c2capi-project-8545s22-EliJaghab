package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<User> formattedStudents = parser.parseUsers(studentsSample);

        assertEquals(formattedStudents.size(), 10);

        assertEquals(formattedStudents.get(0).getName(), "Ramesh Sitaula");

        User.UserType student = User.UserType.Student;
        assertEquals(formattedStudents.get(0).getUserType(), student);

        assertEquals(formattedStudents.get(0).getEmail(), "rsitaula@villanova.edu");

        StudentProfile.Classification graduate = StudentProfile.Classification.Graduate;
        assertEquals(formattedStudents.get(0).getStudentProfile().getClassification(), graduate);

        assertEquals(formattedStudents.get(0).getStudentProfile().getSkills(),
                "TDD, Microservices, Cloud Native Development, Java, Spring");

    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
