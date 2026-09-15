package com.bhupendra.jobrecommendation.controller;


import com.bhupendra.jobrecommendation.dto.response.JobRecommendationResponse;
import com.bhupendra.jobrecommendation.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (limit <= 0) {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "limit must be greater than 0" );
        }
        return recommendationService.getRecommendations(candidateId, limit);
    }
}