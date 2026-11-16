
package com.resume_analyzer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analyses")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;

    private String email;

    private String resumeFileName;

    private String jobRole;

    private Integer resumeScore;

    private Integer matchPercentage;

    // Advanced ATS score
    private Integer atsScore;

    @Column(columnDefinition = "TEXT")
    private String atsScoreBreakdown;

    @Column(columnDefinition = "TEXT")
    private String sectionChecks;

    @Column(columnDefinition = "TEXT")
    private String contactChecks;

    @Column(columnDefinition = "TEXT")
    private String readabilityChecks;

    @Column(columnDefinition = "TEXT")
    private String achievementChecks;

    @Column(columnDefinition = "TEXT")
    private String atsDisclaimer;

    @Column(columnDefinition = "TEXT")
    private String extractedSkills;

    @Column(columnDefinition = "TEXT")
    private String missingSkills;

    @Column(columnDefinition = "TEXT")
    private String suggestions;

    private LocalDateTime createdAt;

    public Analysis() {
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public Integer getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(Integer resumeScore) {
        this.resumeScore = resumeScore;
    }

    public Integer getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(Integer matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public Integer getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(Integer atsScore) {
        this.atsScore = atsScore;
    }

    public String getAtsScoreBreakdown() {
        return atsScoreBreakdown;
    }

    public void setAtsScoreBreakdown(String atsScoreBreakdown) {
        this.atsScoreBreakdown = atsScoreBreakdown;
    }

    public String getSectionChecks() {
        return sectionChecks;
    }

    public void setSectionChecks(String sectionChecks) {
        this.sectionChecks = sectionChecks;
    }

    public String getContactChecks() {
        return contactChecks;
    }

    public void setContactChecks(String contactChecks) {
        this.contactChecks = contactChecks;
    }

    public String getReadabilityChecks() {
        return readabilityChecks;
    }

    public void setReadabilityChecks(String readabilityChecks) {
        this.readabilityChecks = readabilityChecks;
    }

    public String getAchievementChecks() {
        return achievementChecks;
    }

    public void setAchievementChecks(String achievementChecks) {
        this.achievementChecks = achievementChecks;
    }

    public String getAtsDisclaimer() {
        return atsDisclaimer;
    }

    public void setAtsDisclaimer(String atsDisclaimer) {
        this.atsDisclaimer = atsDisclaimer;
    }

    public String getExtractedSkills() {
        return extractedSkills;
    }

    public void setExtractedSkills(String extractedSkills) {
        this.extractedSkills = extractedSkills;
    }

    public String getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(String missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
