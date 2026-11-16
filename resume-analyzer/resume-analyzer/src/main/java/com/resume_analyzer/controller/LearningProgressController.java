
package com.resume_analyzer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resume_analyzer.entity.LearningProgress;
import com.resume_analyzer.service.LearningProgressService;

@RestController
@RequestMapping("/api/learning-progress")
@CrossOrigin(origins = "http://localhost:5173")
public class LearningProgressController {

    private final LearningProgressService service;

    public LearningProgressController(LearningProgressService service) {
        this.service = service;
    }

    // Save learning progress
    @PostMapping
    public ResponseEntity<LearningProgress> saveProgress(
            @RequestBody LearningProgress progress) {

        return ResponseEntity.ok(service.saveProgress(progress));
    }

    // Get all learning progress
    @GetMapping
    public ResponseEntity<List<LearningProgress>> getAllProgress() {

        return ResponseEntity.ok(service.getAllProgress());
    }

    // Get progress by ID
    @GetMapping("/{id}")
    public ResponseEntity<LearningProgress> getProgressById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getProgressById(id));
    }

    // Update completed status
    @PutMapping("/{id}")
    public ResponseEntity<LearningProgress> updateProgress(
            @PathVariable Long id,
            @RequestBody LearningProgress progress) {

        return ResponseEntity.ok(
                service.updateProgress(id, progress));
    }

    // Delete progress
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgress(
            @PathVariable Long id) {

        service.deleteProgress(id);

        return ResponseEntity.ok("Learning progress deleted successfully");
    }
}
