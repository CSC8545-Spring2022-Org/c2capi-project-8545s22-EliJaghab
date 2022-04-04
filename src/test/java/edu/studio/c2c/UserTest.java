package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.studio.c2c.StudentProfile.Classification;
import edu.studio.c2c.User.UserType;

public class UserTest {

    @Test
    public void testHashCode() {
        User user1Student = new User();
        user1Student.setName("User1");
        user1Student.setUserType(UserType.Student);

        User user1StudentDuplicate = new User();
        user1StudentDuplicate.setName("User1");
        user1StudentDuplicate.setUserType(UserType.Student);

        User user2Student = new User();
        user2Student.setName("User2");
        user2Student.setUserType(UserType.Student);

        User user1Faculty = new User();
        user1Faculty.setName("User1");
        user1Faculty.setUserType(UserType.Faculty);

        assertEquals(user1Student.hashCode(), user1StudentDuplicate.hashCode());
        assertNotEquals(user1Student.hashCode(), user2Student.hashCode());
        assertNotEquals(user1Student.hashCode(), user1Faculty.hashCode());
    }

    @Test
    public void testEquals() {
        User user1Student = new User();
        user1Student.setName("User1");
        user1Student.setUserType(UserType.Student);

        User user1StudentDuplicate = new User();
        user1StudentDuplicate.setName("User1");
        user1StudentDuplicate.setUserType(UserType.Student);

        User user2Student = new User();
        user2Student.setName("User2");
        user2Student.setUserType(UserType.Student);

        User user1Faculty = new User();
        user1Faculty.setName("User1");
        user1Faculty.setUserType(UserType.Faculty);

        assertTrue(user1Student.equals(user1StudentDuplicate));
        assertTrue(user1StudentDuplicate.equals(user1Student));
        assertFalse(user1Student.equals(user2Student));
        assertFalse(user1Student.equals(user1Faculty));
    }

    @Test
    public void testToString() {
        User user1Student = new User();
        user1Student.setName("User1");
        user1Student.setUserType(UserType.Student);
        user1Student.setEmail("user@users.edu");

        StudentProfile user1StudentProfile = new StudentProfile();
        user1StudentProfile.setClassification(Classification.Graduate);
        String[] user1StudentSkills = { "java", "python", "TDD" };
        System.out.println(user1StudentSkills);
        user1StudentProfile.setSkills(user1StudentSkills);
        user1Student.setStudentProfile(user1StudentProfile);
        String expectedString = "Name: User1 Email: user@users.edu User Type: Student Classification: Graduate Skills: \"java\", \"python\", \"TDD\"";
        System.out.println(user1Student.toString());

    }

}
