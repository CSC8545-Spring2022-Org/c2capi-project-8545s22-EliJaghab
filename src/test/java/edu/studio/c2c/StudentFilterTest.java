package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
    public void areSkillsValid() {
        String oneSkill = "python";
        assertTrue(filter.areSkillsValid(oneSkill));

        String twoSkills = "python, java";
        assertTrue(filter.areSkillsValid(twoSkills));

        String threeSkills = "python, java, tdd";
        assertTrue(filter.areSkillsValid(threeSkills));

        String wrongNull = "";
        assertFalse(filter.areSkillsValid(wrongNull));

        String wrongSpace = " ";
        assertFalse(filter.areSkillsValid(wrongSpace));

    }

    @Test
    public void testTrimSkills() {
        String oneSkill = "python";
        String[] oneSkillExpected = { "python" };
        assertArrayEquals(filter.trimSkills(oneSkill), oneSkillExpected);

        String oneSkillSpaced = "  python  ";
        String[] oneSkillSpacedExpected = { "python" };
        assertArrayEquals(filter.trimSkills(oneSkillSpaced), oneSkillSpacedExpected);

        String threeSkills = "python, java, tdd";
        String[] threeSkillsExpected = { "python", "java", "tdd" };
        assertArrayEquals(filter.trimSkills(threeSkills), threeSkillsExpected);

        String threeSkillsSpaced = "python  , java  , tdd  ";
        String[] threeSkillsSpacedExpected = { "python", "java", "tdd" };
        assertArrayEquals(filter.trimSkills(threeSkillsSpaced), threeSkillsSpacedExpected);

    }

    @Test
    public void testIsSearchAgainValid() {
        StudentFilter filter = new StudentFilter();

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

}
