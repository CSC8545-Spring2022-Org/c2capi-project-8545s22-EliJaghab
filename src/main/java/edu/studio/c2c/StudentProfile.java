
package edu.studio.c2c;

public class StudentProfile {
    private String classification;
    private String[] skills;

    public StudentProfile(String classification, String[] skills) {
        this.classification = classification;
        this.skills = skills;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

}
