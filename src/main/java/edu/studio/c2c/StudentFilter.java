package edu.studio.c2c;

import java.util.List;
import java.util.Scanner;

public class StudentFilter {

    protected static final String SEARCH_AGAIN_PROMPT = "Want to search again? Enter Y for Yes, N for No:";
    protected static final String SKILLS_PROMPT = "Enter skills for search as a comma-separated string:";
    protected static final String LEVEL_PROMPT = "Enter the student level for search: U for Undergrad, G for Graduate, B for Both";
    protected static final String YES_SEARCH_AGAIN = "Y";
    protected static final String NO_SEARCH_AGAIN = "N";

    protected static final String GRADUATE_LEVEL = "G";
    protected static final String UNDERGRAD_LEVEL = "U";
    protected static final String BOTH_LEVEL = "B";

    public void startConsole(List<User> formattedStudents) {
        String searchAgain = YES_SEARCH_AGAIN;
        while (YES_SEARCH_AGAIN.equals(searchAgain)) {
            String level = getLevel();
            String[] skills = getSkills();
            System.out.println(getCriteriaOutput(level, skills));
            System.out.println(getMatch(formattedStudents, level, skills));
            searchAgain = getSearchAgain();
        }
    }

    public String getLevel() {
        String level = "";
        Scanner scanner = null;
        while (!isLevelValid(level)) {
            scanner = new Scanner(System.in);
            System.out.println(LEVEL_PROMPT);
            level = scanner.nextLine().toUpperCase();
        }
        return level;
    }

    public boolean isLevelValid(String rawLevel) {
        String level = rawLevel.toUpperCase();
        if (level.equals(GRADUATE_LEVEL) || level.equals(UNDERGRAD_LEVEL) || level.equals(BOTH_LEVEL)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String[] getSkills() {
        String rawSkills = "";
        Scanner scanner = null;
        while (!areSkillsValid(rawSkills)) {
            scanner = new Scanner(System.in);
            System.out.println(SKILLS_PROMPT);
            rawSkills = scanner.nextLine();
        }
        String[] skills = trimSkills(rawSkills);
        return skills;
    }

    public boolean areSkillsValid(String skills) {
        if (!"".equals(skills) && !" ".equals(skills)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String[] trimSkills(String rawSkills) {
        String[] rawSkillsSplit = rawSkills.split(",");
        String[] skills = new String[rawSkillsSplit.length];
        for (int i = 0; i < rawSkillsSplit.length; i++) {
            skills[i] = rawSkillsSplit[i].trim();
        }
        return skills;
    }

    public String getSearchAgain() {
        String searchAgain = "";
        Scanner scanner = null;
        while (!isSearchAgainValid(searchAgain)) {
            scanner = new Scanner(System.in);
            System.out.println(SEARCH_AGAIN_PROMPT);
            searchAgain = scanner.nextLine();
        }
        return searchAgain;
    }

    public boolean isSearchAgainValid(String searchAgainRaw) {
        String searchAgain = searchAgainRaw.toUpperCase();
        if (searchAgain.equals(YES_SEARCH_AGAIN) || searchAgain.equals(NO_SEARCH_AGAIN)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getCriteriaOutput(String level, String[] skills) {
        String skillsOutput = formatSkills(skills);
        String levelOutput = formatLevel(level);
        String criteriaOutput = "Selected search criteria: StudentFilter [levelCriterion=" + levelOutput
                + ", skillsCriteria=" + skillsOutput + "]";
        return criteriaOutput;
    }

    public String formatSkills(String[] rawSkills) {
        StringBuilder skillsBuilder = new StringBuilder();
        skillsBuilder.append("[");
        for (String skill : rawSkills) {
            skillsBuilder.append(skill + ", ");
        }
        skillsBuilder.delete(skillsBuilder.length() - 2, skillsBuilder.length());
        skillsBuilder.append("]");
        String skillsOutput = skillsBuilder.toString();
        return skillsOutput;

    }

    public String formatLevel(String rawLevel) {
        String level = "";
        if (rawLevel == "G") {
            level = "Graduate";
        }
        else if (rawLevel == "U") {
            level = "Undergraduate";
        }
        else {
            level = "Both";
        }
        return level;
    }

    public String getMatch(List<User> formattedStudents, String level, String[] skills) {
        if (level != "B") {
            StudentProfile.Classification classification = getClassification(level);
            for (User user : formattedStudents) {
                if (user.getStudentProfile().getClassification() == classification) {

                }

            }

        }
        return level;
    }

    public StudentProfile.Classification getClassification(String level) {
        if (level == "G") {
            return StudentProfile.Classification.Graduate;
        }
        else if (level == "B") {
            return StudentProfile.Classification.Undergraduate;
        }

    }

}
