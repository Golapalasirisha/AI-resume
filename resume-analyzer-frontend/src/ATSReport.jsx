
import "./ATSReport.css";

function ATSReport({ result }) {
  if (!result) {
    return null;
  }

  const breakdown = result.atsScoreBreakdown || {};
  const sections = result.sectionChecks || {};
  const contacts = result.contactChecks || {};
  const readability = result.readabilityChecks || {};
  const achievements = result.achievementChecks || {};

  const score = result.atsScore ?? 0;

  const checks = [
    { name: "Professional Summary", passed: sections.summary },
    { name: "Technical Skills", passed: sections.skills },
    { name: "Projects", passed: sections.projects },
    { name: "Education", passed: sections.education },
    { name: "Experience", passed: sections.experience },
    { name: "Email Address", passed: contacts.email },
    { name: "Phone Number", passed: contacts.phone },
    {
      name: "Measurable Achievements",
      passed: achievements.hasMeasurableAchievements
    }
  ];

  return (
    <section className="ats-report">

      <div className="ats-header">
        <span>ADVANCED ATS ANALYSIS</span>
        <h2>Your ATS Report</h2>
        <p>Detailed analysis of your resume against the job description.</p>
      </div>

      <div className="ats-score-card">
        <div
          className="ats-score-circle"
          style={{
            background: `conic-gradient(#8b5cf6 ${score * 3.6}deg, #e5e7eb 0deg)`
          }}
        >
          <div className="ats-score-inner">
            <h2>{score}</h2>
            <span>/100</span>
          </div>
        </div>

        <div>
          <h3>ATS Compatibility Score</h3>
          <p>
            {score >= 80
              ? "Your resume has strong ATS readiness."
              : score >= 60
              ? "Your resume has moderate ATS readiness."
              : "Your resume needs improvement."}
          </p>
        </div>
      </div>

      <div className="ats-metrics">

        <div className="ats-metric">
          <h3>{result.matchPercentage ?? 0}%</h3>
          <p>Job Match</p>
        </div>

        <div className="ats-metric">
          <h3>{result.resumeScore ?? 0}/100</h3>
          <p>Resume Quality</p>
        </div>

        <div className="ats-metric">
          <h3>{result.matchedSkills?.length ?? 0}</h3>
          <p>Matched Skills</p>
        </div>

        <div className="ats-metric">
          <h3>{result.missingSkills?.length ?? 0}</h3>
          <p>Missing Skills</p>
        </div>

      </div>

      <div className="ats-breakdown">
        <h3>ATS Score Breakdown</h3>

        {[
          ["Section Completeness", breakdown.sectionCompleteness, 30],
          ["Keyword Matching", breakdown.keywordMatching, 40],
          ["Contact Information", breakdown.contactInformation, 10],
          ["Readability", breakdown.readability, 10],
          ["Achievements", breakdown.achievements, 10]
        ].map(([name, value, maximum]) => (
          <div className="ats-breakdown-item" key={name}>
            <div className="ats-breakdown-label">
              <span>{name}</span>
              <strong>{value ?? 0}/{maximum}</strong>
            </div>

            <div className="ats-progress-track">
              <div
                className="ats-progress-fill"
                style={{
                  width: `${Math.min(100, Math.max(0, (Number(value) || 0) / maximum * 100))}%`
                }}
              />
            </div>
          </div>
        ))}
      </div>

      <div className="ats-skills-section">
        <h3>Matched Skills</h3>
        <div className="ats-tags">
          {result.matchedSkills?.length ? (
            result.matchedSkills.map(skill => (
              <span className="ats-matched-tag" key={skill}>
                ✓ {skill}
              </span>
            ))
          ) : "No matched skills."}
        </div>

        <h3>Missing Skills</h3>
        <div className="ats-tags">
          {result.missingSkills?.length ? (
            result.missingSkills.map(skill => (
              <span className="ats-missing-tag" key={skill}>
                + {skill}
              </span>
            ))
          ) : "No missing skills detected."}
        </div>
      </div>

      <div className="ats-checks">
        <h3>Resume Quality Checklist</h3>

        {checks.map(check => (
          <div className="ats-check-item" key={check.name}>
            <span>{check.name}</span>
            <strong className={check.passed ? "passed" : "failed"}>
              {check.passed ? "✓ Passed" : "✗ Needs attention"}
            </strong>
          </div>
        ))}
      </div>

      <div className="ats-extra">
        <h3>Readability Analysis</h3>
        <p>
          Extracted text length:{" "}
          <strong>{readability.extractedTextLength ?? 0} characters</strong>
        </p>

        <h3>Achievement Analysis</h3>
        <p>
          Detected action verbs:{" "}
          {achievements.detectedActionVerbs?.join(", ") || "None detected"}
        </p>
      </div>

      <div className="ats-suggestions">
        <h3>Improvement Suggestions</h3>
        <ul>
          {result.suggestions?.map((suggestion, index) => (
            <li key={index}>{suggestion}</li>
          ))}
        </ul>
      </div>

      <p className="ats-disclaimer">
        {result.atsDisclaimer}
      </p>

    </section>
  );
}

export default ATSReport;
