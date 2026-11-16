\# ResumeAI | AI-Powered Resume Analyzer \& Career Companion



<p align="center">

&#x20; <strong>Analyze smarter. Identify skill gaps. Build your career.</strong>

</p>



<p align="center">

&#x20; A full-stack application designed to help job seekers understand their resume strengths, evaluate job compatibility, and create personalized learning plans for career growth.

</p>



\---



\## Overview



\*\*ResumeAI\*\* is an intelligent resume analysis and job-matching platform built using React, Java, Spring Boot, and MySQL.



The application helps candidates move beyond simply uploading a resume. It analyzes resume content, compares skills against job descriptions, identifies missing competencies, generates an estimated ATS-style score, and provides personalized learning roadmaps.



The goal is to turn resume analysis into an actionable career-improvement experience.



\## Key Features



| Feature | Description |

|---|---|

| Resume Analyzer | Upload PDF resumes and extract their text for analysis. |

| Job Matching | Compare candidate skills with job description requirements. |

| ATS-Style Evaluation | Generate an estimated score with detailed evaluation categories. |

| Skill Gap Detection | Identify matched skills and missing technical skills. |

| Smart Suggestions | Provide recommendations for improving resume quality. |

| Personalized Learning Roadmap | Generate structured learning plans based on missing skills. |

| Learning Progress Tracker | Record completed topics and monitor learning progress. |

| Analysis History | Backend database support for storing resume analysis records. |



\## How ResumeAI Works



```mermaid

flowchart TD

&#x20;   A\["Candidate uploads PDF resume"] --> B\["React Frontend"]

&#x20;   B --> C\["Spring Boot REST API"]

&#x20;   C --> D\["PDF Text Extraction"]

&#x20;   D --> E\["Resume Analysis Engine"]

&#x20;   E --> F\["Job Description Matching"]

&#x20;   F --> G\["ATS-Style Evaluation"]

&#x20;   G --> H\["Analysis Results"]

&#x20;   H --> I\["Skill Gap Identification"]

&#x20;   I --> J\["Personalized Learning Roadmap"]

&#x20;   J --> K\["Learning Progress Tracking"]

&#x20;   C <--> L\[("MySQL Database")]

```



\## Application Modules



\### 1. Resume Analysis

\- Upload a PDF resume.

\- Extract resume text using Apache PDFBox.

\- Identify technical skills.

\- Evaluate resume sections.

\- Store analysis information in MySQL.



\### 2. Job Description Matching

\- Compare resume skills against job requirements.

\- Calculate a skill match percentage.

\- Display matched and missing skills.

\- Help candidates understand their suitability for a target role.



\### 3. ATS-Style Resume Evaluation

\- Evaluate section completeness.

\- Analyze keyword matching.

\- Check contact information.

\- Evaluate readability.

\- Review achievement-related content.

\- Display an estimated ATS-style score and recommendations.



\### 4. Personalized Learning Roadmap

\- Select a target job role.

\- Identify missing skills.

\- Generate a structured, skill-based learning roadmap.

\- Organize learning topics into weekly plans.



\### 5. Learning Progress Tracker

\- Save learning topics.

\- Mark topics as completed.

\- Record completion timestamps.

\- Retrieve, update, and delete progress records.



\## Technology Stack



| Layer | Technologies |

|---|---|

| Frontend | React.js, JavaScript, HTML5, CSS3 |

| Frontend Tooling | Vite, Axios |

| Backend | Java, Spring Boot |

| API Development | Spring Web, REST APIs |

| Persistence | Spring Data JPA, Hibernate |

| Database | MySQL |

| PDF Processing | Apache PDFBox |

| Build Tool | Maven |

| API Testing | Postman |

| IDEs | Eclipse, Visual Studio Code |

| Version Control | Git, GitHub |



\## Project Architecture



```text

AI-resume/

│

├── resume-analyzer-frontend/

│   ├── public/

│   ├── src/

│   │   ├── App.jsx

│   │   ├── LearningRoadmap.jsx

│   │   ├── LearningProgress.jsx

│   │   ├── ATSReport.jsx

│   │   └── CSS files

│   ├── package.json

│   └── vite.config.js

│

├── resume-analyzer/

│   └── resume-analyzer/

│       ├── src/

│       │   ├── main/

│       │   │   ├── java/

│       │   │   │   └── com/resume\_analyzer/

│       │   │   │       ├── controller/

│       │   │   │       ├── entity/

│       │   │   │       ├── repository/

│       │   │   │       └── service/

│       │   │   └── resources/

│       ├── pom.xml

│       └── mvnw

│

├── .gitignore

└── README.md

```



\## Getting Started



Follow these steps to run ResumeAI locally.



\### Prerequisites



Install the following:



\- Java Development Kit (JDK)

\- Node.js and npm

\- MySQL Server

\- Git



You will also need a code editor such as Eclipse or Visual Studio Code.



\### 1. Clone the Repository



```bash

git clone https://github.com/Golapalasirisha/AI-resume.git

cd AI-resume

```



\### 2. Configure the Database



Open MySQL and execute:



```sql

CREATE DATABASE resume\_analyzer;

```



Configure the database connection in your local backend `application.properties` file.



Example configuration:



```properties

spring.datasource.url=jdbc:mysql://localhost:3306/resume\_analyzer

spring.datasource.username=YOUR\_MYSQL\_USERNAME

spring.datasource.password=YOUR\_MYSQL\_PASSWORD



spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true



server.port=8085

```



Replace the example credentials with your own local MySQL credentials.



\*\*Security:\*\* Never upload your actual database password or private credentials to GitHub.



\### 3. Start the Spring Boot Backend



Open a terminal:



```bash

cd resume-analyzer/resume-analyzer

```



Run:



```powershell

.\\mvnw.cmd spring-boot:run

```



Backend address:



`http://localhost:8085`



\### 4. Start the React Frontend



Open a second terminal:



```bash

cd resume-analyzer-frontend

```



Install dependencies:



```bash

npm install

```



Start the development server:



```bash

npm run dev

```



Frontend address:



`http://localhost:5173`



Open the frontend address in your browser to use ResumeAI.



\## REST API Reference



The following are the main backend endpoints.



\### Resume Analysis



| HTTP Method | Endpoint | Purpose |

|---|---|---|

| POST | `/api/resumes/analyze` | Analyze a resume against a job description |

| POST | `/api/resumes/extract` | Extract text from an uploaded PDF |



The analysis endpoint accepts multipart form data, including the resume PDF, job description, and job role.



\### Personalized Learning Roadmap



| HTTP Method | Endpoint | Purpose |

|---|---|---|

| POST | `/api/roadmap/generate` | Generate a personalized learning roadmap |



Example request:



```json

{

&#x20; "jobRole": "Java Developer",

&#x20; "missingSkills": "Hibernate, Microservices, Docker"

}

```



\### Learning Progress



| HTTP Method | Endpoint | Purpose |

|---|---|---|

| POST | `/api/learning-progress` | Create a learning progress record |

| GET | `/api/learning-progress` | Retrieve learning progress records |

| GET | `/api/learning-progress/{id}` | Retrieve a record by ID |

| PUT | `/api/learning-progress/{id}` | Update a progress record |

| DELETE | `/api/learning-progress/{id}` | Delete a progress record |



\## Database



ResumeAI uses MySQL to persist application information.



The database stores information such as:



\- Resume analysis records.

\- Candidate and job-role details.

\- Resume scores and matching results.

\- ATS evaluation information.

\- Learning progress and completion timestamps.



\## Future Enhancements



The following improvements are planned for future versions:



\- Candidate registration and authentication.

\- Downloadable resume improvement reports.

\- Enhanced job-role recommendations.

\- More advanced resume optimization.

\- Cloud deployment.

\- Additional analytics and progress visualizations.



\## Important Disclaimer



The ATS-style score provided by ResumeAI is an estimated evaluation generated using the application's own analysis logic. It is intended as guidance for resume improvement and does not represent an official score from any commercial Applicant Tracking System.



\## About the Developer



\*\*Golapala Sirisha\*\*



Aspiring Java Developer | Spring Boot Developer



B.Tech – Artificial Intelligence and Data Science



\*\*Technical Interests:\*\* Java, Spring Boot, REST APIs, SQL, React, and Full-Stack Development.



\- GitHub: \[GolapalaSirisha](https://github.com/Golapalasirisha)

\- LeetCode: \[Golapala\_Sirisha27](https://leetcode.com/u/Golapala\_Sirisha27/)



\---



<p align="center">

&#x20; <strong>ResumeAI — Turning Resume Insights into Career Progress.</strong>

</p>



<p align="center">

&#x20; If you find this project interesting, consider giving the repository a ⭐

</p>

