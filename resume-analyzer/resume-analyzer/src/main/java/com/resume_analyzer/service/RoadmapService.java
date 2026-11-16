
package com.resume_analyzer.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoadmapService {

    public List<Map<String, Object>> generateRoadmap(
            String jobRole, String missingSkills) {

        List<Map<String, Object>> roadmap = new ArrayList<>();

        if (missingSkills == null || missingSkills.trim().isEmpty()) {
            missingSkills = "Java, SQL, Spring Boot";
        }

        String[] inputSkills = missingSkills.split(",");

        // Remove duplicate skills and keep their original order
        Set<String> skills = new LinkedHashSet<>();

        for (String input : inputSkills) {

            String skill = normalizeSkill(input);

            if (skill == null) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unsupported skill: " + input
                );
            }

            skills.add(skill);
        }

        // Generate a separate four-week roadmap for every skill
        for (String skill : skills) {

            List<Map<String, Object>> skillRoadmap =
                    getSkillRoadmap(skill, jobRole);

            roadmap.addAll(skillRoadmap);
        }

        return roadmap;
    }

    private String normalizeSkill(String input) {

        String skill = input.trim().toLowerCase();

        switch (skill) {

            case "java":
            case "core java":
            case "java programming":
                return "Java";

            case "sql":
            case "mysql":
            case "postgresql":
                return "SQL";

            case "hibernate":
                return "Hibernate";

            case "spring boot":
                return "Spring Boot";

            case "spring data jpa":
                return "Spring Data JPA";

            case "react":
            case "reactjs":
            case "react.js":
                return "React";

            case "javascript":
            case "js":
                return "JavaScript";

            case "python":
                return "Python";

            case "rest api":
            case "rest apis":
                return "REST API";

            case "microservices":
                return "Microservices";

            case "docker":
                return "Docker";

            case "jdbc":
                return "JDBC";

            case "html":
                return "HTML";

            case "css":
                return "CSS";

            case "git":
                return "Git";

            case "maven":
                return "Maven";

            default:
                return null;
        }
    }

    private List<Map<String, Object>> getSkillRoadmap(
            String skill, String jobRole) {

        List<Map<String, Object>> roadmap = new ArrayList<>();

        switch (skill) {

            case "Java":

                addWeek(roadmap, 1, skill, jobRole,
                    "Java Fundamentals",
                    List.of(
                        "Introduction to Java and JVM",
                        "Variables and data types",
                        "Operators and conditional statements",
                        "Loops, arrays and strings"
                    ),
                    "Build a simple calculator using Java."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Object-Oriented Programming",
                    List.of(
                        "Classes and objects",
                        "Constructors and methods",
                        "Inheritance and polymorphism",
                        "Encapsulation and abstraction"
                    ),
                    "Build a Student Management System."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Advanced Java",
                    List.of(
                        "Exception handling",
                        "Collections framework",
                        "Multithreading basics",
                        "File handling"
                    ),
                    "Build a program using Java Collections."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Java Database Project",
                    List.of(
                        "Introduction to JDBC",
                        "Connect Java with MySQL",
                        "Perform CRUD operations",
                        "Handle database exceptions"
                    ),
                    "Build an Employee Management System using Java and MySQL."
                );

                break;

            case "SQL":

                addWeek(roadmap, 1, skill, jobRole,
                    "SQL Fundamentals",
                    List.of(
                        "Introduction to databases",
                        "CREATE, ALTER and DROP",
                        "INSERT, UPDATE and DELETE",
                        "SELECT, WHERE and ORDER BY"
                    ),
                    "Create a Student database and insert records."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "SQL Functions and Grouping",
                    List.of(
                        "COUNT, SUM and AVG",
                        "MIN and MAX",
                        "GROUP BY and HAVING",
                        "DISTINCT and aggregate functions"
                    ),
                    "Create a salary report using SQL queries."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Advanced SQL",
                    List.of(
                        "INNER JOIN",
                        "LEFT JOIN and RIGHT JOIN",
                        "Subqueries",
                        "Views and indexes"
                    ),
                    "Solve 20 SQL interview queries."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Database Project",
                    List.of(
                        "Database design",
                        "Primary and foreign keys",
                        "Relationships between tables",
                        "Complex SQL queries"
                    ),
                    "Build an Employee Database Management System."
                );

                break;

            case "Hibernate":

                addWeek(roadmap, 1, skill, jobRole,
                    "Hibernate Fundamentals",
                    List.of(
                        "Introduction to ORM",
                        "Hibernate architecture",
                        "Hibernate configuration",
                        "SessionFactory and Session"
                    ),
                    "Create a basic Hibernate application."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Entity Mapping",
                    List.of(
                        "@Entity and @Table",
                        "@Id and @GeneratedValue",
                        "@Column annotation",
                        "Hibernate CRUD operations"
                    ),
                    "Create an application to save student details."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Hibernate Relationships",
                    List.of(
                        "One-to-One mapping",
                        "One-to-Many mapping",
                        "Many-to-One mapping",
                        "Many-to-Many mapping"
                    ),
                    "Create a Student and Course relationship."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Hibernate Project",
                    List.of(
                        "HQL queries",
                        "Lazy and eager loading",
                        "Transactions",
                        "Database integration"
                    ),
                    "Build a Library Management System using Hibernate."
                );

                break;

            case "Spring Boot":

                addWeek(roadmap, 1, skill, jobRole,
                    "Spring Boot Fundamentals",
                    List.of(
                        "Introduction to Spring Boot",
                        "Spring Initializr",
                        "Dependency Injection",
                        "Beans and annotations"
                    ),
                    "Create your first Spring Boot application."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "REST API Development",
                    List.of(
                        "@RestController",
                        "@GetMapping and @PostMapping",
                        "@PutMapping and @DeleteMapping",
                        "RequestBody and PathVariable"
                    ),
                    "Build a Student REST API."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Database Integration",
                    List.of(
                        "Spring Data JPA",
                        "JpaRepository",
                        "MySQL connectivity",
                        "CRUD operations"
                    ),
                    "Connect your REST API to a MySQL database."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Advanced Spring Boot Project",
                    List.of(
                        "Exception handling",
                        "Input validation",
                        "Service and repository layers",
                        "Testing APIs using Postman"
                    ),
                    "Build a complete Employee Management REST API."
                );

                break;

            case "React":

                addWeek(roadmap, 1, skill, jobRole,
                    "React Fundamentals",
                    List.of(
                        "Introduction to React",
                        "Components and JSX",
                        "Props",
                        "Rendering lists and conditional rendering"
                    ),
                    "Build a personal profile page using React."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "React State and Events",
                    List.of(
                        "Event handling",
                        "useState hook",
                        "Forms and controlled inputs",
                        "Component communication"
                    ),
                    "Build a To-Do List application."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "React Advanced Concepts",
                    List.of(
                        "useEffect hook",
                        "API integration using fetch",
                        "Axios",
                        "React Router"
                    ),
                    "Build a website that displays data from an API."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "React Project",
                    List.of(
                        "Reusable components",
                        "Frontend validation",
                        "Backend API integration",
                        "Application testing"
                    ),
                    "Build a Job Portal frontend using React."
                );

                break;

            case "Python":

                addWeek(roadmap, 1, skill, jobRole,
                    "Python Fundamentals",
                    List.of(
                        "Python installation and syntax",
                        "Variables and data types",
                        "Operators and conditions",
                        "Loops and functions"
                    ),
                    "Build a simple calculator using Python."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Python Data Structures",
                    List.of(
                        "Lists and tuples",
                        "Sets and dictionaries",
                        "String operations",
                        "List comprehensions"
                    ),
                    "Build a Contact Book application."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Python Advanced Concepts",
                    List.of(
                        "Object-oriented programming",
                        "Exception handling",
                        "File handling",
                        "Modules and packages"
                    ),
                    "Build a file-based Student Management System."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Python Project",
                    List.of(
                        "Working with external libraries",
                        "Reading and writing files",
                        "Database connectivity",
                        "Testing Python programs"
                    ),
                    "Build an Employee Management application using Python."
                );

                break;

            case "REST API":

                addWeek(roadmap, 1, skill, jobRole,
                    "REST API Fundamentals",
                    List.of(
                        "What is an API?",
                        "REST architecture",
                        "Client and server communication",
                        "JSON format"
                    ),
                    "Understand and test a public REST API."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "HTTP Methods and Status Codes",
                    List.of(
                        "GET and POST",
                        "PUT and PATCH",
                        "DELETE",
                        "HTTP status codes"
                    ),
                    "Test different HTTP methods using Postman."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "API Development",
                    List.of(
                        "Request parameters",
                        "Request body and response",
                        "API validation",
                        "Exception handling"
                    ),
                    "Develop a CRUD REST API using Spring Boot."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "REST API Project",
                    List.of(
                        "Database integration",
                        "API testing",
                        "Error response handling",
                        "API documentation"
                    ),
                    "Build a complete Student Management REST API."
                );

                break;

            case "Spring Data JPA":

                addWeek(roadmap, 1, skill, jobRole,
                    "JPA Fundamentals",
                    List.of(
                        "Introduction to JPA",
                        "ORM concepts",
                        "Entity mapping",
                        "Primary key generation"
                    ),
                    "Create a basic JPA entity."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Repository Layer",
                    List.of(
                        "JpaRepository",
                        "CrudRepository",
                        "Derived query methods",
                        "Pagination and sorting"
                    ),
                    "Create a repository for student records."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Entity Relationships",
                    List.of(
                        "One-to-One mapping",
                        "One-to-Many mapping",
                        "Many-to-One mapping",
                        "Many-to-Many mapping"
                    ),
                    "Build related Student and Course entities."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Spring Data JPA Project",
                    List.of(
                        "Custom queries",
                        "JPQL",
                        "Transactions",
                        "Database integration"
                    ),
                    "Build an Employee Management API using Spring Data JPA."
                );

                break;

            case "JavaScript":

                addWeek(roadmap, 1, skill, jobRole,
                    "JavaScript Fundamentals",
                    List.of(
                        "Variables and data types",
                        "Operators and conditions",
                        "Loops",
                        "Functions"
                    ),
                    "Build a simple calculator using JavaScript."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "JavaScript Core Concepts",
                    List.of(
                        "Arrays and objects",
                        "Array methods",
                        "DOM manipulation",
                        "Event handling"
                    ),
                    "Build an interactive To-Do List."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Modern JavaScript",
                    List.of(
                        "Arrow functions",
                        "Promises",
                        "Async and await",
                        "Fetch API"
                    ),
                    "Build a weather information interface using an API."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "JavaScript Project",
                    List.of(
                        "ES6 modules",
                        "Local storage",
                        "Form validation",
                        "Debugging"
                    ),
                    "Build an interactive expense tracker."
                );

                break;

            case "Microservices":

                addWeek(roadmap, 1, skill, jobRole,
                    "Microservices Fundamentals",
                    List.of(
                        "Introduction to microservices",
                        "Monolithic vs microservices",
                        "Service boundaries",
                        "Microservices architecture"
                    ),
                    "Design a simple microservices architecture."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Service Communication",
                    List.of(
                        "REST-based communication",
                        "Synchronous communication",
                        "Asynchronous communication",
                        "Service-to-service calls"
                    ),
                    "Create two Spring Boot services that communicate."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Microservices Infrastructure",
                    List.of(
                        "Service discovery",
                        "API Gateway",
                        "Centralized configuration",
                        "Fault tolerance"
                    ),
                    "Build a small API Gateway demonstration."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Microservices Project",
                    List.of(
                        "Database per service",
                        "Service integration",
                        "Logging and monitoring",
                        "Testing microservices"
                    ),
                    "Build a basic two-service Job Management application."
                );

                break;

            case "Docker":

                addWeek(roadmap, 1, skill, jobRole,
                    "Docker Fundamentals",
                    List.of(
                        "Introduction to Docker",
                        "Docker architecture",
                        "Images and containers",
                        "Basic Docker commands"
                    ),
                    "Run your first application inside a Docker container."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Docker Images",
                    List.of(
                        "Docker Hub",
                        "Pull and run images",
                        "Build custom images",
                        "Dockerfile instructions"
                    ),
                    "Create a Docker image for a sample application."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Docker Networking and Storage",
                    List.of(
                        "Docker volumes",
                        "Container networking",
                        "Port mapping",
                        "Environment variables"
                    ),
                    "Run an application with a database using Docker."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Docker Project",
                    List.of(
                        "Docker Compose",
                        "Multi-container applications",
                        "Application configuration",
                        "Container management"
                    ),
                    "Containerize a Spring Boot and MySQL application."
                );

                break;

            case "JDBC":

                addWeek(roadmap, 1, skill, jobRole,
                    "JDBC Fundamentals",
                    List.of(
                        "Introduction to JDBC",
                        "JDBC architecture",
                        "Database drivers",
                        "Establishing a connection"
                    ),
                    "Connect a Java application to MySQL."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Executing SQL Using JDBC",
                    List.of(
                        "Statement",
                        "PreparedStatement",
                        "ResultSet",
                        "Executing SELECT queries"
                    ),
                    "Retrieve student records using JDBC."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "JDBC CRUD Operations",
                    List.of(
                        "INSERT operations",
                        "UPDATE operations",
                        "DELETE operations",
                        "Transaction management"
                    ),
                    "Create a CRUD application using JDBC."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "JDBC Project",
                    List.of(
                        "Exception handling",
                        "Batch processing",
                        "Connection management",
                        "Database testing"
                    ),
                    "Build a Java Employee Management System using JDBC."
                );

                break;

            case "HTML":

                addWeek(roadmap, 1, skill, jobRole,
                    "HTML Fundamentals",
                    List.of(
                        "HTML document structure",
                        "Headings and paragraphs",
                        "Links and images",
                        "Lists and tables"
                    ),
                    "Create a personal profile webpage."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "HTML Forms",
                    List.of(
                        "Form elements",
                        "Input types",
                        "Labels and buttons",
                        "Form validation attributes"
                    ),
                    "Build a student registration form."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Semantic HTML",
                    List.of(
                        "Header and footer",
                        "Section and article",
                        "Navigation elements",
                        "Accessibility basics"
                    ),
                    "Build a structured multi-section webpage."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "HTML Project",
                    List.of(
                        "Organizing webpage content",
                        "Embedding media",
                        "Semantic structure",
                        "Browser testing"
                    ),
                    "Build a complete portfolio webpage using HTML."
                );

                break;

            case "CSS":

                addWeek(roadmap, 1, skill, jobRole,
                    "CSS Fundamentals",
                    List.of(
                        "CSS syntax and selectors",
                        "Colors and backgrounds",
                        "Fonts and text styling",
                        "Margins and padding"
                    ),
                    "Style your personal profile webpage."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "CSS Layouts",
                    List.of(
                        "Box model",
                        "Display properties",
                        "Flexbox",
                        "CSS Grid"
                    ),
                    "Create a responsive card layout."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Responsive Design",
                    List.of(
                        "Media queries",
                        "Responsive units",
                        "Positioning",
                        "Transitions and animations"
                    ),
                    "Create a responsive navigation bar."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "CSS Project",
                    List.of(
                        "Reusable CSS classes",
                        "Responsive page design",
                        "Hover effects",
                        "Cross-device testing"
                    ),
                    "Design a complete responsive portfolio website."
                );

                break;

            case "Git":

                addWeek(roadmap, 1, skill, jobRole,
                    "Git Fundamentals",
                    List.of(
                        "Introduction to version control",
                        "Git installation",
                        "git init and git status",
                        "git add and git commit"
                    ),
                    "Create your first Git repository."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Git Branching",
                    List.of(
                        "Creating branches",
                        "Switching branches",
                        "Merging branches",
                        "Resolving merge conflicts"
                    ),
                    "Practice branching with a sample project."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "GitHub Collaboration",
                    List.of(
                        "Creating GitHub repositories",
                        "Push and pull",
                        "Clone repositories",
                        "Pull requests"
                    ),
                    "Upload a Java project to GitHub."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Git Project Workflow",
                    List.of(
                        "Working with remote repositories",
                        "Commit best practices",
                        "Branching strategies",
                        "Project collaboration"
                    ),
                    "Manage a complete project using Git and GitHub."
                );

                break;

            case "Maven":

                addWeek(roadmap, 1, skill, jobRole,
                    "Maven Fundamentals",
                    List.of(
                        "Introduction to Maven",
                        "Maven installation",
                        "Project directory structure",
                        "pom.xml basics"
                    ),
                    "Create your first Maven project."
                );

                addWeek(roadmap, 2, skill, jobRole,
                    "Maven Dependencies",
                    List.of(
                        "Adding dependencies",
                        "Dependency scopes",
                        "Maven repositories",
                        "Dependency management"
                    ),
                    "Add and use dependencies in a Java application."
                );

                addWeek(roadmap, 3, skill, jobRole,
                    "Maven Build Lifecycle",
                    List.of(
                        "Clean lifecycle",
                        "Compile and test",
                        "Package and install",
                        "Maven plugins"
                    ),
                    "Build and package a Java application using Maven."
                );

                addWeek(roadmap, 4, skill, jobRole,
                    "Maven Project",
                    List.of(
                        "Build configuration",
                        "Project profiles",
                        "Testing integration",
                        "Dependency troubleshooting"
                    ),
                    "Create a complete Maven-based Java project."
                );

                break;

            default:
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Roadmap not available for " + skill
                );
        }

        return roadmap;
    }

    private void addWeek(
            List<Map<String, Object>> roadmap,
            int weekNumber,
            String skill,
            String jobRole,
            String title,
            List<String> topics,
            String task) {

        Map<String, Object> week = new LinkedHashMap<>();

        week.put("week", weekNumber);
        week.put("title", "Week " + weekNumber + " - " + title);
        week.put("skill", skill);
        week.put("topics", topics);
        week.put("task", task);
        week.put("jobRole", jobRole);

        roadmap.add(week);
    }
}
