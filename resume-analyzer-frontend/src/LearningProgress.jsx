
import { useEffect, useState } from "react";
import "./LearningProgress.css";

function LearningProgress({ jobRole = "Java Developer" }) {
  const [progress, setProgress] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const API_URL = "http://localhost:8085/api/learning-progress";

  const fetchProgress = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await fetch(API_URL);

      if (!response.ok) {
        throw new Error("Unable to load learning progress.");
      }

      const data = await response.json();

      setProgress(
        data.filter((item) => item.jobRole === jobRole)
      );
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProgress();
  }, [jobRole]);

  const toggleComplete = async (item) => {
    try {
      const response = await fetch(`${API_URL}/${item.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          completed: !item.completed,
        }),
      });

      if (!response.ok) {
        throw new Error("Unable to update progress.");
      }

      const updatedItem = await response.json();

      setProgress((previous) =>
        previous.map((topic) =>
          topic.id === updatedItem.id ? updatedItem : topic
        )
      );
    } catch (err) {
      setError(err.message);
    }
  };

  const completedCount = progress.filter(
    (item) => item.completed
  ).length;

  const percentage =
    progress.length === 0
      ? 0
      : Math.round((completedCount / progress.length) * 100);

  return (
    <div className="progress-page">
      <div className="progress-heading">
        <span>YOUR LEARNING JOURNEY</span>
        <h1>Learning Progress Tracker</h1>
        <p>Track your completed topics and achieve your career goals.</p>
      </div>

      <div className="progress-summary">
        <h2>{percentage}% Completed</h2>

        <div className="progress-bar">
          <div
            className="progress-fill"
            style={{ width: `${percentage}%` }}
          ></div>
        </div>

        <p>
          {completedCount} of {progress.length} topics completed
        </p>
      </div>

      {loading && <p>Loading progress...</p>}

      {error && <p className="progress-error">{error}</p>}

      {!loading && progress.length === 0 && (
        <div className="progress-empty">
          <h3>No learning topics found</h3>
          <p>Add topics to your learning tracker to begin.</p>
        </div>
      )}

      <div className="progress-topic-list">
        {progress.map((item) => (
          <div className="progress-topic-card" key={item.id}>
            <div>
              <h3>{item.topic}</h3>
              <p>{item.skill}</p>
              {item.completed && (
                <small>
                  Completed:{" "}
                  {new Date(item.completedAt).toLocaleDateString()}
                </small>
              )}
            </div>

            <button
              className={
                item.completed
                  ? "completed-btn"
                  : "pending-btn"
              }
              onClick={() => toggleComplete(item)}
            >
              {item.completed ? "Completed ✓" : "Mark Complete"}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default LearningProgress;
