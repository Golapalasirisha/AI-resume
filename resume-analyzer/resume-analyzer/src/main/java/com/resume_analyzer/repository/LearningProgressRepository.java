
package com.resume_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.resume_analyzer.entity.LearningProgress;

public interface LearningProgressRepository
        extends JpaRepository<LearningProgress, Long> {
}
