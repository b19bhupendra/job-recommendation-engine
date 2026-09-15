package com.bhupendra.jobrecommendation.dto.response;

public record JobRecommendationResponse(
        String jobId,
        String title,
        double score,
        MatchBreakdown breakdown
) {
}