
package com.resume_analyzer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resume_analyzer.entity.Analysis;
import com.resume_analyzer.repository.AnalysisRepository;

@Service
public class AnalysisService {

    private final AnalysisRepository analysisRepository;

    // Constructor injection
    public AnalysisService(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    // Save analysis
    public Analysis saveAnalysis(Analysis analysis) {
        return analysisRepository.save(analysis);
    }

    // Get all analyses
    public List<Analysis> getAllAnalyses() {
        return analysisRepository.findAll();
    }

    // Get analysis by ID
    public Optional<Analysis> getAnalysisById(Long id) {
        return analysisRepository.findById(id);
    }

    // Delete analysis
    public void deleteAnalysis(Long id) {
        analysisRepository.deleteById(id);
    }
}
