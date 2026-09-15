package com.bhupendra.jobrecommendation.controller;


import com.bhupendra.jobrecommendation.dto.response.JobRecommendationResponse;
import com.bhupendra.jobrecommendation.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{candidateId}")
    public List<JobRecommendationResponse> getRecommendations(
            @PathVariable String candidateId,
            @RequestParam(defaultValue = "5") int limit) {

        return recommendationService.getRecommendations(candidateId, limit);
    }
}