\# ResumeAI – AI Resume Analyzer \& Job Matcher



ResumeAI is a full-stack web application designed to help job seekers analyze their resumes, match their skills with job descriptions, identify missing skills, and improve their career readiness.



\## Features



\- PDF Resume Upload and Analysis

\- Job Description and Resume Matching

\- ATS-Style Resume Score

\- Matched and Missing Skills Detection

\- Smart Resume Improvement Suggestions

\- Personalized Learning Roadmap

\- Learning Progress Tracking



\## Technologies Used



\*\*Frontend:\*\* React.js, JavaScript, HTML, CSS, Axios, Vite



\*\*Backend:\*\* Java, Spring Boot, Spring Data JPA, Maven, PDFBox



\*\*Database:\*\* MySQL



\*\*Tools:\*\* Eclipse, VS Code, Postman, Git, GitHub



\## Project Structure



\- `resume-analyzer-frontend` – React frontend

\- `resume-analyzer/resume-analyzer` – Spring Boot backend



\## How to Run



\### Backend



```bash

cd resume-analyzer/resume-analyzer

.\\mvnw.cmd spring-boot:run

```



Backend: `http://localhost:8085`



\### Frontend



Open another terminal:



```bash

cd resume-analyzer-frontend

npm install

npm run dev

```



Frontend: `http://localhost:5173`



\### Database



Create the MySQL database:



```sql

CREATE DATABASE resume\_analyzer;

```



Configure your database credentials in your local `application.properties` file.



\## Main API Endpoints



| Method | Endpoint | Purpose |

|---|---|---|

| POST | `/api/resumes/analyze` | Analyze resume |

| POST | `/api/resumes/extract` | Extract PDF text |

| POST | `/api/roadmap/generate` | Generate learning roadmap |

| POST | `/api/learning-progress` | Save learning progress |

| GET | `/api/learning-progress` | View learning progress |



\## ATS Score Disclaimer



The ATS score is an estimated score based on the application's own analysis logic. It is not an official score from a commercial ATS platform.



\## Future Enhancements



\- User Authentication

\- Resume Download

\- Advanced Job Recommendations

\- Cloud Deployment



\## Author



\*\*Golapala Sirisha\*\*



Java Developer | Spring Boot Developer



\- GitHub: https://github.com/Golapalasirisha

\- LeetCode: https://leetcode.com/u/Golapala\_Sirisha27/



\---



⭐ Thank you for visiting ResumeAI!

