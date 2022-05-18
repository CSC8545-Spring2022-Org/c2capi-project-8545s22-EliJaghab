
package edu.studio.c2c;

public class StudentProfile {

    protected enum Classification {
        Graduate, Undergraduate
    }

    private String skills;
    private Classification classification;

    public StudentProfile() {
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
