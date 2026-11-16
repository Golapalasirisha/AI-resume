
package com.resume_analyzer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.resume_analyzer.entity.Analysis;
import com.resume_analyzer.service.AnalysisService;
import com.resume_analyzer.service.PdfService;
import com.resume_analyzer.service.ResumeAnalysisService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "http://localhost:5173")
public class PdfController {

    private final PdfService pdfService;
    private final ResumeAnalysisService resumeAnalysisService;
    private final AnalysisService analysisService;
    private final ObjectMapper objectMapper;

    public PdfController(
            PdfService pdfService,
            ResumeAnalysisService resumeAnalysisService,
            AnalysisService analysisService,
            ObjectMapper objectMapper) {

        this.pdfService = pdfService;
        this.resumeAnalysisService = resumeAnalysisService;
        this.analysisService = analysisService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/extract")
    public ResponseEntity<?> extractPdfText(
            @RequestParam("file") MultipartFile file) {

        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Please upload a PDF file.");
            }

            String fileName = file.getOriginalFilename();

            if (fileName == null ||
                    !fileName.toLowerCase().endsWith(".pdf")) {
                return ResponseEntity.badRequest()
                        .body("Only PDF files are allowed.");
            }

            String extractedText = pdfService.extractText(file);

            return ResponseEntity.ok(extractedText);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Unable to read the PDF file.");
        }
    }

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(

            @RequestParam("file") MultipartFile file,

            @RequestParam("jobDescription") String jobDescription,

            @RequestParam(value = "candidateName", required = false)
            String candidateName,

            @RequestParam(value = "email", required = false)
            String email,

            @RequestParam("jobRole") String jobRole) {

        try {

            // 1. Validate PDF file
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Please upload a PDF resume.");
            }

            String fileName = file.getOriginalFilename();

            if (fileName == null ||
                    !fileName.toLowerCase().endsWith(".pdf")) {

                return ResponseEntity.badRequest()
                        .body("Only PDF files are allowed.");
            }

            // 2. Validate job description
            if (jobDescription == null ||
                    jobDescription.isBlank()) {

                return ResponseEntity.badRequest()
                        .body("Please enter a job description.");
            }

            // 3. Validate job role
            if (jobRole == null || jobRole.isBlank()) {

                return ResponseEntity.badRequest()
                        .body("Please enter the job role.");
            }

            // 4. Extract resume text
            String resumeText = pdfService.extractText(file);

            // 5. Analyze resume
            Map<String, Object> result =
                    resumeAnalysisService.analyzeResume(
                            resumeText, jobDescription);

            // 6. Create Analysis entity
            Analysis analysis = new Analysis();

            analysis.setCandidateName(candidateName);
            analysis.setEmail(email);
            analysis.setJobRole(jobRole);
            analysis.setResumeFileName(fileName);

            // 7. Save existing analysis results
            analysis.setMatchPercentage(
                    (Integer) result.get("matchPercentage"));

            analysis.setResumeScore(
                    (Integer) result.get("resumeScore"));

            analysis.setExtractedSkills(
                    String.join(", ",
                            (List<String>) result.get("extractedSkills")));

            analysis.setMissingSkills(
                    String.join(", ",
                            (List<String>) result.get("missingSkills")));

            analysis.setSuggestions(
                    String.join("; ",
                            (List<String>) result.get("suggestions")));

            // 8. Save Advanced ATS score
            analysis.setAtsScore(
                    (Integer) result.get("atsScore"));

            // 9. Save detailed ATS breakdown as JSON
            analysis.setAtsScoreBreakdown(
                    objectMapper.writeValueAsString(
                            result.get("atsScoreBreakdown")));

            analysis.setSectionChecks(
                    objectMapper.writeValueAsString(
                            result.get("sectionChecks")));

            analysis.setContactChecks(
                    objectMapper.writeValueAsString(
                            result.get("contactChecks")));

            analysis.setReadabilityChecks(
                    objectMapper.writeValueAsString(
                            result.get("readabilityChecks")));

            analysis.setAchievementChecks(
                    objectMapper.writeValueAsString(
                            result.get("achievementChecks")));

            analysis.setAtsDisclaimer(
                    (String) result.get("atsDisclaimer"));

            // 10. Save everything in MySQL
            Analysis savedAnalysis =
                    analysisService.saveAnalysis(analysis);

            // 11. Return complete response
            result.put("resumeFileName", fileName);
            result.put("savedAnalysis", savedAnalysis);

            return ResponseEntity.ok(result);

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Unable to process the PDF resume.");
        }
    }
}
