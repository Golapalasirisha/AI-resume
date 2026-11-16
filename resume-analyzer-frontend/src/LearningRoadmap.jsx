
import { useState, useEffect } from "react";
import "./LearningRoadmap.css";

function LearningRoadmap({
  jobRole: initialJobRole,
  missingSkills: initialMissingSkills,
  candidateName = "Candidate"
}) {
  const [jobRole, setJobRole] = useState(
    initialJobRole || "Java Developer"
  );

  const [missingSkills, setMissingSkills] = useState(
    Array.isArray(initialMissingSkills)
      ? initialMissingSkills.join(", ")
      : initialMissingSkills || "Hibernate, Microservices, Docker"
  );

  const [roadmap, setRoadmap] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [saveMessage, setSaveMessage] = useState("");

  const API_URL = "http://localhost:8085/api/learning-progress";

  useEffect(() => {
    if (initialJobRole) {
      setJobRole(initialJobRole);
    }

    if (initialMissingSkills !== undefined) {
      setMissingSkills(
        Array.isArray(initialMissingSkills)
          ? initialMissingSkills.join(", ")
          : initialMissingSkills || ""
      );
    }
  }, [initialJobRole, initialMissingSkills]);

  // Save roadmap topics into Learning Progress
  const saveTopicsToProgress = async (roadmapData) => {
    try {
      setSaveMessage("Saving topics to Learning Progress...");

      // Get topics already saved in the database
      const existingResponse = await fetch(API_URL);

      if (!existingResponse.ok) {
        throw new Error("Could not check existing learning progress.");
      }

      const existingTopics = await existingResponse.json();

      let savedCount = 0;
      let skippedCount = 0;

      // Save each topic from the generated roadmap
      for (const week of roadmapData) {
        for (const topic of week.topics) {

          // Avoid saving duplicate topics
          const alreadyExists = existingTopics.some(
            (item) =>
              item.candidateName === candidateName &&
              item.jobRole === jobRole &&
              item.skill === week.skill &&
              item.topic === topic
          );

          if (alreadyExists) {
            skippedCount++;
            continue;
          }

          const saveResponse = await fetch(API_URL, {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              candidateName: candidateName,
              jobRole: jobRole,
              skill: week.skill,
              topic: topic,
              completed: false
            })
          });

          if (!saveResponse.ok) {
            throw new Error("Unable to save a learning topic.");
          }

          savedCount++;
        }
      }

      setSaveMessage(
        `Successfully saved ${savedCount} new topics. ${skippedCount} existing topics skipped.`
      );

    } catch (err) {
      setSaveMessage(
        "Roadmap generated, but saving progress failed: " + err.message
      );
    }
  };

  const generateRoadmap = async (event) => {
    event.preventDefault();

    setLoading(true);
    setError("");
    setSaveMessage("");
    setRoadmap([]);

    try {
      const response = await fetch(
        "http://localhost:8085/api/roadmap/generate",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            jobRole: jobRole,
            missingSkills: missingSkills
          })
        }
      );

      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));

        throw new Error(
          errorData.message || "Unable to generate roadmap. Please check your skills."
        );
      }

      const data = await response.json();

      setRoadmap(data);

      // Automatically save generated topics
      await saveTopicsToProgress(data);

    } catch (err) {
      setError(err.message || "Something went wrong.");
    } finally {
      setLoading(false);
    }
  };

  // Group roadmap weeks by skill
  const groupedRoadmaps = roadmap.reduce((groups, week) => {
    if (!groups[week.skill]) {
      groups[week.skill] = [];
    }

    groups[week.skill].push(week);
    return groups;
  }, {});

  return (
    <div className="roadmap-page">

      <div className="roadmap-heading">
        <span className="roadmap-tag">
          PERSONALIZED DEVELOPMENT
        </span>

        <h1>Your Learning Roadmap</h1>

        <p>
          Turn your skill gaps into a practical learning journey.
          Follow your weekly plan and improve your job readiness.
        </p>
      </div>

      <form className="roadmap-form" onSubmit={generateRoadmap}>

        <div className="roadmap-field">
          <label>Target Job Role</label>

          <input
            type="text"
            value={jobRole}
            onChange={(e) => setJobRole(e.target.value)}
            placeholder="Example: Java Developer"
            required
          />
        </div>

        <div className="roadmap-field">
          <label>Skills You Need to Learn</label>

          <textarea
            value={missingSkills}
            onChange={(e) => setMissingSkills(e.target.value)}
            placeholder="Example: Hibernate, Docker, Microservices"
            rows="3"
            required
          />

          <small>Separate each skill with a comma.</small>
        </div>

        <button
          type="submit"
          className="roadmap-generate-btn"
          disabled={loading}
        >
          {loading ? "Generating..." : "Generate My Roadmap"}
        </button>

      </form>

      {error && (
        <div className="roadmap-error">{error}</div>
      )}

      {saveMessage && (
        <div className="roadmap-task">
          {saveMessage}
        </div>
      )}

      {roadmap.length > 0 && (
        <section className="roadmap-results">

          <div className="roadmap-results-heading">
            <h2>Your Skill-Based Learning Plans</h2>
            <p>Target role: {jobRole}</p>
          </div>

          {Object.entries(groupedRoadmaps).map(
            ([skill, weeks]) => (
              <div key={skill} className="roadmap-skill-section">

                <h2>{skill} Learning Roadmap</h2>

                <div className="roadmap-timeline">

                  {weeks.map((week) => (
                    <article
                      className="roadmap-card"
                      key={`${week.skill}-${week.week}`}
                    >
                      <div className="roadmap-week">
                        WEEK {week.week}
                      </div>

                      <h3>{week.title}</h3>

                      <div className="roadmap-skill">
                        Focus skill: {week.skill}
                      </div>

                      <h4>Topics to learn</h4>

                      <ul>
                        {week.topics.map((topic, index) => (
                          <li key={index}>{topic}</li>
                        ))}
                      </ul>

                      <div className="roadmap-task">
                        <strong>Weekly task</strong>
                        <p>{week.task}</p>
                      </div>

                    </article>
                  ))}

                </div>
              </div>
            )
          )}

        </section>
      )}

      {!roadmap.length && !loading && !error && (
        <div className="roadmap-empty">
          <div className="roadmap-empty-icon">✦</div>

          <h3>Your learning journey starts here</h3>

          <p>
            Enter your target role and missing skills, then generate
            your personalized weekly learning plan.
          </p>
        </div>
      )}

    </div>
  );
}

export default LearningRoadmap;
