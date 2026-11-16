
package com.resume_analyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resume_analyzer.entity.LearningProgress;
import com.resume_analyzer.repository.LearningProgressRepository;

@Service
public class LearningProgressService {

    private final LearningProgressRepository repository;

    public LearningProgressService(LearningProgressRepository repository) {
        this.repository = repository;
    }

    // Save learning progress
    public LearningProgress saveProgress(LearningProgress progress) {
        return repository.save(progress);
    }

    // Get all learning progress
    public List<LearningProgress> getAllProgress() {
        return repository.findAll();
    }

    // Get progress by ID
    public LearningProgress getProgressById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Learning progress not found: " + id));
    }

    // Update learning progress
    public LearningProgress updateProgress(Long id, LearningProgress updatedProgress) {

        LearningProgress existing = getProgressById(id);

        existing.setCompleted(updatedProgress.isCompleted());

        return repository.save(existing);
    }

    // Delete learning progress
    public void deleteProgress(Long id) {
        repository.deleteById(id);
    }
}
