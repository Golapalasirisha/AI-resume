
package com.resume_analyzer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ResumeAnalysisService {

    private final List<String> skillList = Arrays.asList(
            "Java", "Python", "JavaScript", "React",
            "HTML", "CSS", "Spring Boot",
            "Spring Data JPA", "Hibernate", "JDBC",
            "REST API", "SQL", "MySQL", "PostgreSQL",
            "MongoDB", "Microservices", "Docker",
            "AWS", "Git", "Maven", "JUnit",
            "Bootstrap", "Node.js"
    );

    public Map<String, Object> analyzeResume(
            String resumeText, String jobDescription) {

        if (resumeText == null) {
            resumeText = "";
        }

        if (jobDescription == null) {
            jobDescription = "";
        }

        // 1. Detect resume and job description skills
        List<String> resumeSkills = findSkills(resumeText);
        List<String> requiredSkills = findSkills(jobDescription);

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : requiredSkills) {
            if (resumeSkills.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        // 2. Calculate keyword match percentage
        int matchPercentage = 0;

        if (!requiredSkills.isEmpty()) {
            matchPercentage = (matchedSkills.size() * 100)
                    / requiredSkills.size();
        }

        // 3. Existing resume quality score
        int resumeScore = calculateResumeScore(resumeText);

        // 4. Check resume sections
        Map<String, Object> sectionChecks =
                checkResumeSections(resumeText);

        int sectionScore =
                (int) sectionChecks.get("score");

        // 5. Check contact information
        Map<String, Object> contactChecks =
                checkContactInformation(resumeText);

        int contactScore =
                (int) contactChecks.get("score");

        // 6. Check readability
        Map<String, Object> readabilityChecks =
                checkReadability(resumeText);

        int readabilityScore =
                (int) readabilityChecks.get("score");

        // 7. Check achievements and action words
        Map<String, Object> achievementChecks =
                checkAchievements(resumeText);

        int achievementScore =
                (int) achievementChecks.get("score");

        // 8. Calculate ATS keyword score out of 40
        int keywordScore = 0;

        if (!requiredSkills.isEmpty()) {
            keywordScore = matchPercentage * 40 / 100;
        }

        // 9. Calculate final ATS compatibility score
        int atsScore = sectionScore
                + keywordScore
                + contactScore
                + readabilityScore
                + achievementScore;

        // 10. Generate improvement suggestions
        List<String> suggestions = new ArrayList<>();

        if (missingSkills.isEmpty() && !requiredSkills.isEmpty()) {
            suggestions.add(
                    "Your resume contains all detected required skills."
            );
        } else {
            for (String skill : missingSkills) {
                suggestions.add(
                        "Consider adding relevant experience or projects "
                        + "related to " + skill + "."
                );
            }
        }

        if (requiredSkills.isEmpty()) {
            suggestions.add(
                    "No recognized technical skills were detected "
                    + "in the job description. Check the job description."
            );
        }

        if (!(boolean) sectionChecks.get("summary")) {
            suggestions.add(
                    "Add a professional summary or career objective."
            );
        }

        if (!(boolean) sectionChecks.get("skills")) {
            suggestions.add(
                    "Add a clearly labeled Skills section."
            );
        }

        if (!(boolean) sectionChecks.get("projects")) {
            suggestions.add(
                    "Add relevant academic or personal projects."
            );
        }

        if (!(boolean) sectionChecks.get("education")) {
            suggestions.add(
                    "Add your educational qualifications."
            );
        }

        if (!(boolean) sectionChecks.get("experience")) {
            suggestions.add(
                    "Add internship, training, or experience details "
                    + "where applicable."
            );
        }

        if (!(boolean) contactChecks.get("email")) {
            suggestions.add(
                    "Include a valid email address in your resume."
            );
        }

        if (!(boolean) contactChecks.get("phone")) {
            suggestions.add(
                    "Include your phone number in your resume."
            );
        }

        if (readabilityScore < 10) {
            suggestions.add(
                    "Add more relevant resume content and ensure "
                    + "the PDF text is readable."
            );
        }

        if (achievementScore < 10) {
            suggestions.add(
                    "Use action verbs and measurable achievements "
                    + "to describe your work and projects."
            );
        }

        // 11. Detailed ATS score breakdown
        Map<String, Object> atsBreakdown = new LinkedHashMap<>();

        atsBreakdown.put("sectionCompleteness", sectionScore);
        atsBreakdown.put("keywordMatching", keywordScore);
        atsBreakdown.put("contactInformation", contactScore);
        atsBreakdown.put("readability", readabilityScore);
        atsBreakdown.put("achievements", achievementScore);
        atsBreakdown.put("totalScore", atsScore);

        // 12. Final response
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("extractedSkills", resumeSkills);
        result.put("requiredSkills", requiredSkills);
        result.put("matchedSkills", matchedSkills);
        result.put("missingSkills", missingSkills);

        result.put("matchPercentage", matchPercentage);

        // Existing score
        result.put("resumeScore", resumeScore);

        // New advanced ATS results
        result.put("atsScore", atsScore);
        result.put("atsScoreBreakdown", atsBreakdown);
        result.put("sectionChecks", sectionChecks);
        result.put("contactChecks", contactChecks);
        result.put("readabilityChecks", readabilityChecks);
        result.put("achievementChecks", achievementChecks);

        result.put("suggestions", suggestions);

        result.put(
                "atsDisclaimer",
                "This is an estimated ATS-readiness score based on "
                + "our own evaluation criteria. Actual ATS systems "
                + "may use different scoring methods."
        );

        return result;
    }

    // Existing resume quality score
    private int calculateResumeScore(String resumeText) {

        String text = resumeText.toLowerCase();

        int score = 0;

        if (text.contains("professional summary")
                || text.contains("career objective")
                || text.contains("summary")) {
            score += 20;
        }

        if (text.contains("technical skills")
                || text.contains("skills")) {
            score += 20;
        }

        if (text.contains("projects")
                || text.contains("project")) {
            score += 20;
        }

        if (text.contains("education")
                || text.contains("qualification")) {
            score += 20;
        }

        if (text.contains("experience")
                || text.contains("internship")) {
            score += 20;
        }

        return score;
    }

    // Check important resume sections
    private Map<String, Object> checkResumeSections(
            String resumeText) {

        String text = resumeText.toLowerCase();

        boolean summary =
                text.contains("professional summary")
                || text.contains("career objective")
                || text.contains("summary");

        boolean skills =
                text.contains("technical skills")
                || text.contains("skills");

        boolean projects =
                text.contains("projects")
                || text.contains("project");

        boolean education =
                text.contains("education")
                || text.contains("qualification");

        boolean experience =
                text.contains("experience")
                || text.contains("internship");

        int score = 0;

        if (summary) score += 6;
        if (skills) score += 6;
        if (projects) score += 6;
        if (education) score += 6;
        if (experience) score += 6;

        Map<String, Object> checks = new LinkedHashMap<>();

        checks.put("summary", summary);
        checks.put("skills", skills);
        checks.put("projects", projects);
        checks.put("education", education);
        checks.put("experience", experience);
        checks.put("score", score);
        checks.put("maximumScore", 30);

        return checks;
    }

    // Check email and phone number
    private Map<String, Object> checkContactInformation(
            String resumeText) {

        boolean email = Pattern.compile(
                "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}",
                Pattern.CASE_INSENSITIVE
        ).matcher(resumeText).find();

        boolean phone = Pattern.compile(
                "(?<!\\d)(?:\\+?\\d{1,3}[ -]?)?\\d{10}(?!\\d)"
        ).matcher(resumeText).find();

        int score = 0;

        if (email) score += 5;
        if (phone) score += 5;

        Map<String, Object> checks = new LinkedHashMap<>();

        checks.put("email", email);
        checks.put("phone", phone);
        checks.put("score", score);
        checks.put("maximumScore", 10);

        return checks;
    }

    // Check extracted text length
    private Map<String, Object> checkReadability(
            String resumeText) {

        int length = resumeText.trim().length();

        int score = 0;

        if (length >= 300) {
            score += 5;
        }

        if (length >= 1000) {
            score += 5;
        }

        Map<String, Object> checks = new LinkedHashMap<>();

        checks.put("extractedTextLength", length);
        checks.put("score", score);
        checks.put("maximumScore", 10);

        return checks;
    }

    // Check action verbs and measurable achievements
    private Map<String, Object> checkAchievements(
            String resumeText) {

        String text = resumeText.toLowerCase();

        List<String> actionVerbs = Arrays.asList(
                "developed", "built", "designed",
                "implemented", "created", "managed",
                "improved", "optimized", "automated",
                "achieved", "led", "tested"
        );

        List<String> detectedActionVerbs = new ArrayList<>();

        for (String verb : actionVerbs) {

            String regex = "\\b" + Pattern.quote(verb) + "\\b";

            if (Pattern.compile(regex).matcher(text).find()) {
                detectedActionVerbs.add(verb);
            }
        }

        boolean hasNumbers = Pattern.compile(
                "\\b\\d+(?:\\.\\d+)?\\s*(?:%|percent|users|records|"
                + "projects|hours|months|years)?\\b",
                Pattern.CASE_INSENSITIVE
        ).matcher(text).find();

        int score = 0;

        if (!detectedActionVerbs.isEmpty()) {
            score += 5;
        }

        if (hasNumbers) {
            score += 5;
        }

        Map<String, Object> checks = new LinkedHashMap<>();

        checks.put("detectedActionVerbs", detectedActionVerbs);
        checks.put("hasMeasurableAchievements", hasNumbers);
        checks.put("score", score);
        checks.put("maximumScore", 10);

        return checks;
    }

    // Detect technical skills
    private List<String> findSkills(String text) {

        List<String> foundSkills = new ArrayList<>();

        for (String skill : skillList) {

            String regex = "(?i)(?<![A-Za-z0-9])"
                    + Pattern.quote(skill)
                    + "(?![A-Za-z0-9])";

            if (Pattern.compile(regex).matcher(text).find()) {
                foundSkills.add(skill);
            }
        }

        return foundSkills;
    }
}
