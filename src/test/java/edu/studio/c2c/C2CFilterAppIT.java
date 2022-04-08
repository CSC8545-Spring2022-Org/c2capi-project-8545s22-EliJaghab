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
        // args[0] =
        // "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImVQU29ZZ0ZZS2hzWmF2RFBnMmF5dCJ9.eyJodHRwczovL3d3dy5oZWxsby5jb20vcm9sZXMiOltdLCJpc3MiOiJodHRwczovL2Rldi13anBpbG1pYy51cy5hdXRoMC5jb20vIiwic3ViIjoiZ29vZ2xlLW9hdXRoMnwxMDQxMTg2Mzc5NTAwNjYwMzgxNDEiLCJhdWQiOiJodHRwczovL3d3dy5oZWxsby5jb20vIiwiaWF0IjoxNjQ5MzgyNTQ4LCJleHAiOjE2NDk0Njg5NDgsImF6cCI6IlJ4bER1TTNVQ294eVRSNXpuc1hQcGE0QTU5ZDB4UFluIiwic2NvcGUiOiIiLCJwZXJtaXNzaW9ucyI6W119.l8k5exX01V8h2lgQujKiHAuMfLn8Qqm6sXUpAz5MrQSpTIzyXnwKr2d1sKudzA85_LMyJnFrKkFTpT561cPFoxPv1TH2lXRVAKQVXO_nIUZEOhORiS__ti30vQHKDb3MOErGiS4jF3VUDxMV4fARDbQRHJSUg6Kcbk8n7p2xsBd5qR8qnnUGyQe8vy7YQdFRxBKDjsROxf1LcZSo_8aosX-AcynefMDlMorhoybFd7e61e5MMZq_aBzJU1OgJ3SeeR-tC2S8M_4DBC9tnFnWKWZ8rOjYPZ0EUSQ6-bZrdDTAvHsBwOmt8LJqUuRx-1OhTBCGtTA0j6pX4qmIOhzZEg";

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
        String token = System.getProperty("bearer.token");
        return token;
    }
}
