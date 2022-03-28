package edu.studio.c2c;

import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String userType;
    private StudentProfile studentProfile;

    public User(String name, String email, String userType, StudentProfile studentProfile) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.studentProfile = studentProfile;
    }

    public boolean equals(User user) {
        if (this.name.equals(user.name) && this.userType.equals(userType)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(name, userType);
    }

    public String toString() {
        return name + " " + email + " " + userType + " " + studentProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}
