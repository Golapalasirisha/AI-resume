
import { useState } from "react";
import axios from "axios";
import LearningRoadmap from "./LearningRoadmap";
import LearningProgress from "./LearningProgress";
import ATSReport from "./ATSReport";
import "./App.css";

function App() {
  const [activePage, setActivePage] = useState("home");

  const [file, setFile] = useState(null);
  const [candidateName, setCandidateName] = useState("");
  const [email, setEmail] = useState("");
  const [jobRole, setJobRole] = useState("");
  const [jobDescription, setJobDescription] = useState("");

  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!file) {
      setError("Please upload your PDF resume.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);
    formData.append("candidateName", candidateName);
    formData.append("email", email);
    formData.append("jobRole", jobRole);
    formData.append("jobDescription", jobDescription);

    try {
      setLoading(true);
      setError("");
      setResult(null);

      const response = await axios.post(
        "http://localhost:8085/api/resumes/analyze",
        formData
      );

      setResult(response.data);
    } catch (err) {
      setError(
        typeof err.response?.data === "string"
          ? err.response.data
          : "Unable to analyze resume. Check your backend."
      );
    } finally {
      setLoading(false);
    }
  };

  const goToAnalyze = () => {
    setActivePage("analyze");
  };

  const goToRoadmap = () => {
    setActivePage("roadmap");
  };

  const goToProgress = () => {
    setActivePage("progress");
  };

  return (
    <div className="app">

      {/* Navigation Bar */}
      <nav className="navbar">
        <div className="logo" onClick={() => setActivePage("home")}>
          <span>📄</span> ResumeAI
        </div>

        <div className="nav-links">
          <button
            className={activePage === "home" ? "active" : ""}
            onClick={() => setActivePage("home")}
          >
            Home
          </button>

          <button
            className={activePage === "analyze" ? "active" : ""}
            onClick={goToAnalyze}
          >
            Analyze Resume
          </button>

          <button
            className={activePage === "roadmap" ? "active" : ""}
            onClick={goToRoadmap}
          >
            Learning Roadmap
          </button>

          <button
            className={activePage === "progress" ? "active" : ""}
            onClick={goToProgress}
          >
            Learning Progress
          </button>

          <button
            className={activePage === "history" ? "active" : ""}
            onClick={() => setActivePage("history")}
          >
            History
          </button>

          <button
            className={activePage === "about" ? "active" : ""}
            onClick={() => setActivePage("about")}
          >
            About
          </button>
        </div>
      </nav>

      {/* Home Page */}
      {activePage === "home" && (
        <section className="home-page">
          <div className="hero">
            <span className="hero-tag">
              AI POWERED RESUME ANALYZER
            </span>

            <h1>
              Build a Stronger Resume.
              <br />
              <span>Find Your Dream Job.</span>
            </h1>

            <p>
              Analyze your resume, identify missing skills,
              calculate your job match percentage and improve
              your chances of getting hired.
            </p>

            <button className="hero-button" onClick={goToAnalyze}>
              Get Started →
            </button>
          </div>

          <div className="features">
            <div className="feature-card">
              <span>📄</span>
              <h3>Resume Analysis</h3>
              <p>Upload your PDF resume and analyze its content.</p>
            </div>

            <div className="feature-card">
              <span>🎯</span>
              <h3>Job Matching</h3>
              <p>Compare your skills with job requirements.</p>
            </div>

            <div className="feature-card">
              <span>💡</span>
              <h3>Smart Suggestions</h3>
              <p>Discover missing skills and improvement areas.</p>
            </div>

            <div className="feature-card">
              <span>🚀</span>
              <h3>Learning Roadmap</h3>
              <p>Get a weekly plan to improve your missing skills.</p>
              <button onClick={goToRoadmap}>
                Explore Roadmap →
              </button>
            </div>

            <div className="feature-card">
              <span>📊</span>
              <h3>Learning Progress</h3>
              <p>Track completed topics and measure your learning progress.</p>
              <button onClick={goToProgress}>
                Track Progress →
              </button>
            </div>
          </div>
        </section>
      )}

      {/* Analyze Resume Page */}
      {activePage === "analyze" && (
        <main className="container">
          <section className="form-card">
            <h2>Analyze Your Resume</h2>
            <p>Enter your details and upload your resume.</p>

            <form onSubmit={handleSubmit}>
              <label>Candidate Name</label>
              <input
                type="text"
                placeholder="Enter your name"
                value={candidateName}
                onChange={(e) => setCandidateName(e.target.value)}
              />

              <label>Email Address</label>
              <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />

              <label>Job Role *</label>
              <input
                type="text"
                placeholder="Example: Java Developer"
                value={jobRole}
                onChange={(e) => setJobRole(e.target.value)}
                required
              />

              <label>Upload Resume (PDF) *</label>
              <input
                type="file"
                accept=".pdf,application/pdf"
                onChange={(e) => setFile(e.target.files[0] || null)}
                required
              />

              <label>Job Description *</label>
              <textarea
                rows="6"
                placeholder="Paste job description here..."
                value={jobDescription}
                onChange={(e) => setJobDescription(e.target.value)}
                required
              />

              <button type="submit" disabled={loading}>
                {loading ? "Analyzing..." : "Analyze Resume"}
              </button>
            </form>

            {error && <p className="error">{error}</p>}
          </section>

          {/* Existing Analysis Results */}
          {result && (
            <>
              <section className="result-card">
                <h2>Analysis Results</h2>

                <div className="score-container">
                  <div className="score-box">
                    <h3>{result.matchPercentage}%</h3>
                    <p>Job Match</p>
                  </div>

                  <div className="score-box">
                    <h3>{result.resumeScore}/100</h3>
                    <p>Resume Quality</p>
                  </div>
                </div>

                <h3>Extracted Skills</h3>
                <div className="skills">
                  {result.extractedSkills?.map(skill => (
                    <span key={skill}>{skill}</span>
                  ))}
                </div>

                <h3>Matched Skills</h3>
                <div className="skills matched">
                  {result.matchedSkills?.map(skill => (
                    <span key={skill}>{skill}</span>
                  ))}
                </div>

                <h3>Missing Skills</h3>
                <div className="skills missing">
                  {result.missingSkills?.length
                    ? result.missingSkills.map(skill => (
                        <span key={skill}>{skill}</span>
                      ))
                    : "No missing skills detected."}
                </div>

                <h3>Suggestions</h3>
                <ul>
                  {result.suggestions?.map((suggestion, index) => (
                    <li key={index}>{suggestion}</li>
                  ))}
                </ul>

                <p>
                  <strong>Resume:</strong> {result.resumeFileName}
                </p>

                <p>
                  <strong>Record ID:</strong> {result.savedAnalysis?.id}
                </p>

                <button
                  className="hero-button"
                  onClick={goToRoadmap}
                >
                  Create My Learning Roadmap →
                </button>
              </section>

              {/* New Advanced ATS Report */}
              <ATSReport result={result} />
            </>
          )}
        </main>
      )}

      {/* Learning Roadmap Page */}
      {activePage === "roadmap" && (
        <LearningRoadmap
          jobRole={jobRole || "Java Developer"}
          missingSkills={result?.missingSkills || []}
          candidateName={candidateName || "Candidate"}
        />
      )}

      {/* Learning Progress Page */}
      {activePage === "progress" && (
        <LearningProgress
          jobRole={jobRole || "Java Developer"}
        />
      )}

      {/* History Page */}
      {activePage === "history" && (
        <main className="container">
          <section className="form-card">
            <h2>Analysis History</h2>
            <p>Your previously analyzed resumes will appear here.</p>
            <button onClick={goToAnalyze}>
              Analyze New Resume
            </button>
          </section>
        </main>
      )}

      {/* About Page */}
      {activePage === "about" && (
        <main className="container">
          <section className="form-card">
            <h2>About ResumeAI</h2>
            <p>
              ResumeAI is a resume analysis and job matching
              application that helps candidates understand how
              their skills match job requirements.
            </p>

            <h3>Technologies Used</h3>
            <div className="skills">
              <span>Java</span>
              <span>Spring Boot</span>
              <span>React</span>
              <span>MySQL</span>
              <span>REST API</span>
              <span>PDFBox</span>
            </div>
          </section>
        </main>
      )}

      {/* Footer */}
      <footer className="footer">
        <p>© 2026 ResumeAI | AI Resume Analyzer</p>
      </footer>

    </div>
  );
}

export default App;
