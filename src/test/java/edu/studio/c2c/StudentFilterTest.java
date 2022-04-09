package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.studio.c2c.User.UserType;

public class StudentFilterTest {
    private StudentFilter filter = new StudentFilter();

    @Test
    public void testIsLevelValid() {
        String graduate = "G";
        assertTrue(filter.isLevelValid(graduate));

        String graduateLower = "g";
        assertTrue(filter.isLevelValid(graduateLower));

        String undergraduate = "U";
        assertTrue(filter.isLevelValid(undergraduate));

        String both = "B";
        assertTrue(filter.isLevelValid(both));

        String wrongLetter = "Q";
        assertFalse(filter.isLevelValid(wrongLetter));

        String wrongNumber = "5";
        assertFalse(filter.isLevelValid(wrongNumber));

        String wrongWord = "wrong";
        assertFalse(filter.isLevelValid(wrongWord));

        String wrongSpace = " ";
        assertFalse(filter.isLevelValid(wrongSpace));

        String wrongNull = " ";
        assertFalse(filter.isLevelValid(wrongNull));
    }

    @Test
    public void testTrimSkills() {
        String oneSkill = "python";
        String[] oneSkillExpected = { "python" };
        assertArrayEquals(filter.trimAndLowerSkills(oneSkill), oneSkillExpected);

        String oneSkillSpaced = "  python  ";
        String[] oneSkillSpacedExpected = { "python" };
        assertArrayEquals(filter.trimAndLowerSkills(oneSkillSpaced), oneSkillSpacedExpected);

        String threeSkills = "python, java, tdd";
        String[] threeSkillsExpected = { "python", "java", "tdd" };
        assertArrayEquals(filter.trimAndLowerSkills(threeSkills), threeSkillsExpected);

        String threeSkillsSpaced = "python  , java  , tdd  ";
        String[] threeSkillsSpacedExpected = { "python", "java", "tdd" };
        assertArrayEquals(filter.trimAndLowerSkills(threeSkillsSpaced), threeSkillsSpacedExpected);

    }

    @Test
    public void testIsSearchAgainValid() {
        String yes = "y";
        assertTrue(filter.isSearchAgainValid(yes));

        String yesUpper = "Y";
        assertTrue(filter.isSearchAgainValid(yesUpper));

        String no = "n";
        assertTrue(filter.isSearchAgainValid(no));

        String noUpper = "N";
        assertTrue(filter.isSearchAgainValid(noUpper));

        String wrongLetter = "Q";
        assertFalse(filter.isSearchAgainValid(wrongLetter));

        String wrongNumber = "5";
        assertFalse(filter.isSearchAgainValid(wrongNumber));

        String wrongWord = "wrong";
        assertFalse(filter.isSearchAgainValid(wrongWord));

        String wrongSpace = " ";
        assertFalse(filter.isSearchAgainValid(wrongSpace));

        String wrongNull = " ";
        assertFalse(filter.isSearchAgainValid(wrongNull));
    }

    @Test
    public void testGetCriteriaOutput() {
        String[] skills = { "python", "java", "TDD" };
        String level = "G";
        String expectedOutput = "Selected search criteria: StudentFilter [levelCriterion=Graduate, skillsCriteria=[python, java, TDD]]";
        assertEquals(filter.getCriteriaOutput(level, skills), expectedOutput);
    }

    @Test
    public void testFormatSkills() {
        String[] rawSkills = { "python", "java", "TDD" };
        String expectedSkills = "[python, java, TDD]";
        assertEquals(filter.formatSkills(rawSkills), expectedSkills);
    }

    @Test
    public void testFormatLevel() {
        assertEquals(filter.formatLevel("G"), "Graduate");
        assertEquals(filter.formatLevel("U"), "Undergraduate");
        assertEquals(filter.formatLevel("B"), "Both");
    }

    @Test
    public void testGetMatch() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);
        List<User> formattedStudents = parser.parseUsers(studentsSample);
        String[] searchSkills = { "python", "java" };
        String actualResponse = filter.getMatch(formattedStudents, "G", searchSkills);
        String expectedResponse = "Matched 1 of total 10" + "\n" + formattedStudents.get(1).toString() + "\n";
        assertEquals(actualResponse, expectedResponse);
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @Test
    public void testGetMatchedStudentsOutput() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);
        List<User> formattedStudents = parser.parseUsers(studentsSample);
        User firstStudent = formattedStudents.get(0);
        formattedStudents.clear();
        formattedStudents.add(firstStudent);
        assertEquals(firstStudent.toString() + "\n", filter.getMatchedStudentsOutput(formattedStudents));
    }

    @Test
    public void testGetMatchCountOutput() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);
        List<User> formattedStudents = parser.parseUsers(studentsSample);
        assertEquals("Matched 5 of total 10", filter.getMatchCountOutput(5, formattedStudents));
    }

    @Test
    public void testDoesStudentSkillsMatch() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);
        List<User> formattedStudents = parser.parseUsers(studentsSample);
        User firstUser = formattedStudents.get(0);
        String[] correctMatch = { "TDD", "Microservices", "Spring" };
        String[] correctLowerMatch = { "tdd", "microservices", "spring" };
        String[] incorrectPartialMatch = { "TDD", "Mircoservices", "Spring", "Python" };
        assertTrue(filter.doesStudentSkillsMatch(correctMatch, firstUser));
        assertTrue(filter.doesStudentSkillsMatch(correctLowerMatch, firstUser));
        assertFalse(filter.doesStudentSkillsMatch(incorrectPartialMatch, firstUser));

        User noSkills = formattedStudents.get(9);
        String[] blankSearch = { "" };
        assertTrue(filter.doesStudentSkillsMatch(blankSearch, firstUser));
    }

    @Test
    public void testDoesLevelMatch() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);
        List<User> formattedStudents = parser.parseUsers(studentsSample);
        User firstUser = formattedStudents.get(0);
        List<StudentProfile.Classification> filtersGraduate = new ArrayList<StudentProfile.Classification>();
        filtersGraduate.add(StudentProfile.Classification.Graduate);
        filtersGraduate.add(StudentProfile.Classification.Graduate);
        List<StudentProfile.Classification> filtersUndergraduate = new ArrayList<StudentProfile.Classification>();
        filtersUndergraduate.add(StudentProfile.Classification.Undergraduate);
        filtersUndergraduate.add(StudentProfile.Classification.Undergraduate);

        assertTrue(filter.doesLevelMatch(filtersGraduate, firstUser));
        assertFalse(filter.doesLevelMatch(filtersUndergraduate, firstUser));
    }

    @Test
    public void testGetLevelFilters() {
        List<StudentProfile.Classification> filtersGraduate = new ArrayList<StudentProfile.Classification>();
        filtersGraduate.add(StudentProfile.Classification.Graduate);
        filtersGraduate.add(StudentProfile.Classification.Graduate);
        List<StudentProfile.Classification> filtersUndergraduate = new ArrayList<StudentProfile.Classification>();
        filtersUndergraduate.add(StudentProfile.Classification.Undergraduate);
        filtersUndergraduate.add(StudentProfile.Classification.Undergraduate);
        List<StudentProfile.Classification> filtersBoth = new ArrayList<StudentProfile.Classification>();
        filtersBoth.add(StudentProfile.Classification.Graduate);
        filtersBoth.add(StudentProfile.Classification.Undergraduate);

        assertEquals(filter.getLevelFilters("G"), filtersGraduate);
        assertEquals(filter.getLevelFilters("U"), filtersUndergraduate);
        assertEquals(filter.getLevelFilters("B"), filtersBoth);
    }

    @Test
    public void testIsStudent() throws Exception {
        UserParser parser = new UserParser();
        String studentsSamplePath = "src/test/resources/sample-students-response.json";
        String studentsSample = readFileAsString(studentsSamplePath);
        List<User> formattedStudents = parser.parseUsers(studentsSample);
        User firstUser = formattedStudents.get(0);
        assertTrue(filter.isStudent(firstUser));

        firstUser.setUserType(UserType.Faculty);
        assertFalse(filter.isStudent(firstUser));

    }

}
