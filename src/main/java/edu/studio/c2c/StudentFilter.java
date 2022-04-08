package edu.studio.c2c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.studio.c2c.StudentProfile.Classification;

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
        scanner = new Scanner(System.in);
        System.out.println(SKILLS_PROMPT);
        rawSkills = scanner.nextLine();
        String[] skills = trimAndLowerSkills(rawSkills);
        return skills;
    }

    public String[] trimAndLowerSkills(String rawSkills) {
        String[] rawSkillsSplit = rawSkills.split(",");
        String[] skills = new String[rawSkillsSplit.length];
        for (int i = 0; i < rawSkillsSplit.length; i++) {
            skills[i] = rawSkillsSplit[i].trim().toLowerCase();
        }
        return skills;
    }

    public String getSearchAgain() {
        String searchAgain = "";
        Scanner scanner = null;
        while (!isSearchAgainValid(searchAgain)) {
            scanner = new Scanner(System.in);
            System.out.println(SEARCH_AGAIN_PROMPT);
            searchAgain = scanner.nextLine().toUpperCase();
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
        if ("G".equals(rawLevel)) {
            level = "Graduate";
        }
        else if ("U".equals(rawLevel)) {
            level = "Undergraduate";
        }
        else {
            level = "Both";
        }
        return level;
    }

    public String getMatch(List<User> formattedStudents, String level, String[] searchSkills) {
        StringBuilder matchesBuilder = new StringBuilder();
        List<Classification> filters = getLevelFilters(level);
        Integer matchedCount = 0;
        List<User> matchedStudents = new ArrayList<User>();
        for (User user : formattedStudents) {
            if (isStudent(user)) {
                if (doesLevelMatch(filters, user)) {
                    if (doesStudentSkillsMatch(searchSkills, user)) {
                        matchedCount++;
                        matchedStudents.add(user);
                    }
                }
            }
        }
        matchesBuilder.append(getMatchCountOutput(matchedCount, formattedStudents));
        matchesBuilder.append("\n");
        matchesBuilder.append(getMatchedStudentsOutput(matchedStudents));
        String matchOutput = matchesBuilder.toString();
        return matchOutput;
    }

    public boolean doesStudentSkillsMatch(String[] searchSkills, User user) {
        String skills = user.getStudentProfile().getSkills();
        Integer matchCount = 0;
        if ("[]".equals(Arrays.toString(searchSkills))) {
            return true;
        }
        if (skills != null) {
            String[] skillsSplit = skills.toLowerCase().split(",");
            for (String searchSkill : searchSkills) {
                for (String skill : skillsSplit) {
                    if (searchSkill.equalsIgnoreCase(skill.trim())) {
                        matchCount++;
                        break;
                    }
                }
            }
        }
        boolean match = matchCount == searchSkills.length;
        return match;
    }

    public boolean isStudent(User user) {
        return user.getUserType() == User.UserType.Student;
    }

    public String getMatchedStudentsOutput(List<User> matchedStudents) {
        StringBuilder studentMatchesBuilder = new StringBuilder();
        for (User user : matchedStudents) {
            studentMatchesBuilder.append(user.toString());
            studentMatchesBuilder.append("\n");
        }
        String studentMatchesOutput = studentMatchesBuilder.toString();
        return studentMatchesOutput;

    }

    public String getMatchCountOutput(Integer matchedCount, List<User> formattedStudents) {
        StringBuilder matchCountOutputBuilder = new StringBuilder();
        Integer totalStudents = formattedStudents.size();
        matchCountOutputBuilder.append("Matched " + matchedCount + " of total " + totalStudents);
        String matchCountOutput = matchCountOutputBuilder.toString();
        return matchCountOutput;
    }

    public boolean doesLevelMatch(List<Classification> filters, User user) {
        StudentProfile.Classification firstFilter = filters.get(0);
        StudentProfile.Classification secondFilter = filters.get(1);
        StudentProfile.Classification classification = user.getStudentProfile().getClassification();
        if (classification == firstFilter || classification == secondFilter) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Classification> getLevelFilters(String level) {
        List<StudentProfile.Classification> filters = new ArrayList<StudentProfile.Classification>();
        if (level == "G") {
            filters.add(StudentProfile.Classification.Graduate);
            filters.add(StudentProfile.Classification.Graduate);
        }
        else if (level == "U") {
            filters.add(StudentProfile.Classification.Undergraduate);
            filters.add(StudentProfile.Classification.Undergraduate);
        }
        else {
            filters.add(StudentProfile.Classification.Graduate);
            filters.add(StudentProfile.Classification.Undergraduate);
        }
        return filters;
    }
}
