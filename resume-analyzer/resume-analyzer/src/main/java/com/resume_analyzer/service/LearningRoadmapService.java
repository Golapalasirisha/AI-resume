
package com.resume_analyzer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.resume_analyzer.entity.Analysis;
import com.resume_analyzer.repository.AnalysisRepository;

@Service
public class LearningRoadmapService {

    private final AnalysisRepository analysisRepository;

    public LearningRoadmapService(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    public Map<String, Object> generateRoadmap(Long analysisId) {

        // Find the saved resume analysis
        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() ->
                        new RuntimeException("Resume analysis not found with ID: " + analysisId));

        String jobRole = analysis.getJobRole();
        String missingSkills = analysis.getMissingSkills();

        List<String> skills = new ArrayList<>();

        // Read missing skills from the database
        if (missingSkills != null && !missingSkills.isBlank()) {
            skills = Arrays.stream(missingSkills.split(","))
                    .map(String::trim)
                    .filter(skill -> !skill.isEmpty())
                    .toList();
        }

        List<Map<String, Object>> weeks = new ArrayList<>();

        // Create a learning plan for each missing skill
        if (skills.isEmpty()) {

            weeks.add(createWeek(
                    1,
                    "Strengthen your " + jobRole + " fundamentals",
                    "Revise the important concepts related to your target role.",
                    List.of(
                            "Review core concepts",
                            "Practice coding questions",
                            "Build a small practical example"
                    )
            ));

            weeks.add(createWeek(
                    2,
                    "Prepare for technical interviews",
                    "Improve your practical knowledge and interview confidence.",
                    List.of(
                            "Practice common interview questions",
                            "Revise your resume projects",
                            "Solve coding problems"
                    )
            ));

        } else {

            int weekNumber = 1;

            for (String skill : skills) {

                if (weekNumber > 6) {
                    break;
                }

                weeks.add(createWeek(
                        weekNumber,
                        "Learn " + skill,
                        "Build your knowledge of " + skill
                                + " for your " + jobRole + " career.",
                        getTasksForSkill(skill)
                ));

                weekNumber++;
            }

            weeks.add(createWeek(
                    weekNumber,
                    "Practical project and interview preparation",
                    "Apply your newly learned skills in a practical project.",
                    List.of(
                            "Build a mini project using your missing skills",
                            "Add the project to your resume",
                            "Practice technical interview questions"
                    )
            ));
        }

        Map<String, Object> roadmap = new LinkedHashMap<>();

        roadmap.put("analysisId", analysis.getId());
        roadmap.put("candidateName", analysis.getCandidateName());
        roadmap.put("jobRole", jobRole);
        roadmap.put("resumeScore", analysis.getResumeScore());
        roadmap.put("matchPercentage", analysis.getMatchPercentage());
        roadmap.put("missingSkills", skills);
        roadmap.put("totalWeeks", weeks.size());
        roadmap.put("weeks", weeks);

        return roadmap;
    }

    private Map<String, Object> createWeek(
            int weekNumber,
            String title,
            String description,
            List<String> tasks) {

        Map<String, Object> week = new LinkedHashMap<>();

        week.put("week", weekNumber);
        week.put("title", title);
        week.put("description", description);
        week.put("tasks", tasks);
        week.put("completed", false);

        return week;
    }

    private List<String> getTasksForSkill(String skill) {

        String name = skill.toLowerCase();

        if (name.contains("hibernate")) {
            return List.of(
                    "Understand ORM and Hibernate architecture",
                    "Learn entity classes and annotations",
                    "Practice entity relationships",
                    "Connect Hibernate with Spring Boot",
                    "Build CRUD operations"
            );
        }

        if (name.contains("microservices")) {
            return List.of(
                    "Understand microservices architecture",
                    "Learn how REST APIs communicate",
                    "Understand service discovery",
                    "Study API Gateway concepts",
                    "Build two small communicating services"
            );
        }

        if (name.contains("docker")) {
            return List.of(
                    "Understand containers and images",
                    "Install Docker Desktop",
                    "Learn common Docker commands",
                    "Create a Dockerfile",
                    "Run your Spring Boot application using Docker"
            );
        }

        if (name.contains("spring")) {
            return List.of(
                    "Revise Spring Core and dependency injection",
                    "Learn Spring Boot annotations",
                    "Practice REST API development",
                    "Connect Spring Boot with MySQL",
                    "Build a CRUD application"
            );
        }

        if (name.contains("sql") || name.contains("mysql")) {
            return List.of(
                    "Revise SQL fundamentals",
                    "Practice joins and subqueries",
                    "Learn GROUP BY and HAVING",
                    "Practice database relationships",
                    "Solve SQL interview questions"
            );
        }

        if (name.contains("java")) {
            return List.of(
                    "Revise Java OOP concepts",
                    "Practice Collections and exception handling",
                    "Learn Java 8 features",
                    "Practice coding problems",
                    "Build a small Java application"
            );
        }

        return List.of(
                "Understand the fundamentals of " + skill,
                "Study important concepts and terminology",
                "Follow beginner-level tutorials",
                "Practice hands-on examples",
                "Build a small project using " + skill
        );
    }
}
