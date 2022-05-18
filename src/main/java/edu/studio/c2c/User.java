package edu.studio.c2c;

import java.util.Objects;

public class User {

    protected enum UserType {
        Student, Faculty, Company
    }

    private String name;
    private String email;
    private UserType userType;
    private StudentProfile studentProfile;

    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userType);
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (this == obj) {
            isEqual = true;
        }
        else if (obj == null) {
            isEqual = false;
        }
        else if (getClass() != obj.getClass()) {
            isEqual = false;
        }
        else {
            User other = (User) obj;
            isEqual = Objects.equals(name, other.name) && userType == other.userType;
        }
        return isEqual;
    }

    public String toString() {
        return "User: [name=" + this.getName() + ", email=" + this.getEmail() + ", userType=" + this.getUserType()
                + ", studentProfile=StudentProfile [classification=" + this.getStudentProfile().getClassification()
                + ", skills=[" + this.getStudentProfile().getSkills() + "]]";
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType student) {
        this.userType = student;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}
