
package com.resume_analyzer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resume_analyzer.service.RoadmapService;

@RestController
@RequestMapping("/api/roadmap")
@CrossOrigin(origins = "http://localhost:5173")
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Map<String, Object>>> generateRoadmap(
            @RequestBody Map<String, String> request) {

        String jobRole = request.getOrDefault("jobRole", "Software Developer");
        String missingSkills = request.getOrDefault("missingSkills", "");

        List<Map<String, Object>> roadmap =
                roadmapService.generateRoadmap(jobRole, missingSkills);

        return ResponseEntity.ok(roadmap);
    }
}
