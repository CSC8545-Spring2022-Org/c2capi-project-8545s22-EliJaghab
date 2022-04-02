package edu.studio.c2c;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.studio.c2c.User.UserType;

public class UserTest {

    @Test
    public void testEqualsSymmetric() {
        User x = new User();
        x.setName("Hello");
        x.setUserType(UserType.Student);

        User y = new User();
        y.setName("Hello");
        y.setUserType(UserType.Student);

        assertTrue(x.equals(y));
        assertTrue(y.equals(x));
    }
}
