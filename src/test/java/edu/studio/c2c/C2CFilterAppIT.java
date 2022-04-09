package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

public class C2CFilterAppIT {

    protected static final String YES_SEARCH_AGAIN = "Y";

    @Test
    public void endToEndIntegrationTest() {
        String[] args = new String[1];
        args[0] = obtainBearerTokenUsingSystemProps();
        C2CFilterApp filter = new C2CFilterApp();
        if (filter.validateBearerToken(args)) {
            String rawStudents = filter.getRawStudents(args[0]);
            UserParser parser = new UserParser();
            List<User> formattedStudents = parser.parseUsers(rawStudents);
            StudentFilter studentFilter = new StudentFilter();
            String searchAgain = YES_SEARCH_AGAIN;

            while (YES_SEARCH_AGAIN.equals(searchAgain)) {
                String level = "B";
                String[] skills = { "python", "java" };
                String expectedCriteria = "Selected search criteria: StudentFilter [levelCriterion=Both, skillsCriteria=[python, java]]";
                String expectedMatches = "Matched 1 of total 10\n"
                        + "User: [name=Nick Langan, email=nlangan@villanova.edu, userType=Student, studentProfile=StudentProfile [classification=Graduate, skills=[Flutter, Python, Java]]\n"
                        + "\n";
                String expectedOutput = expectedCriteria + "\n" + expectedMatches;
                ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
                System.setOut(new PrintStream(newConsole));
                System.out.println(studentFilter.getCriteriaOutput(level, skills));
                System.out.println(studentFilter.getMatch(formattedStudents, level, skills));
                assertEquals(newConsole.toString(), expectedOutput);
                searchAgain = "N";
            }
        }
    }

    protected String obtainBearerTokenUsingSystemProps() {
        String token = System.getProperty("c2capi.token");
        return token;
    }
}
