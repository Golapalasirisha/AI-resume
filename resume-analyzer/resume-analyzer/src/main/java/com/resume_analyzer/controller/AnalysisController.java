
package com.resume_analyzer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resume_analyzer.entity.Analysis;
import com.resume_analyzer.service.AnalysisService;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "http://localhost:5173")
public class AnalysisController {

    private final AnalysisService analysisService;

    // Constructor injection
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    // Save analysis
    @PostMapping
    public ResponseEntity<Analysis> saveAnalysis(
            @RequestBody Analysis analysis) {

        Analysis savedAnalysis =
                analysisService.saveAnalysis(analysis);

        return ResponseEntity.ok(savedAnalysis);
    }

    // Get all analyses (History)
    @GetMapping("/history")
    public ResponseEntity<List<Analysis>> getAllAnalyses() {

        List<Analysis> analyses =
                analysisService.getAllAnalyses();

        return ResponseEntity.ok(analyses);
    }

    // Get analysis by ID
    @GetMapping("/{id}")
    public ResponseEntity<Analysis> getAnalysisById(
            @PathVariable Long id) {

        return analysisService.getAnalysisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete analysis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalysis(
            @PathVariable Long id) {

        if (analysisService.getAnalysisById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        analysisService.deleteAnalysis(id);

        return ResponseEntity.noContent().build();
    }
}
