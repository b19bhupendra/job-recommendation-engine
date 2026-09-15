package com.bhupendra.jobrecommendation.dto.response;

public record MatchBreakdown(
        double skills,
        double experience,
        double location,
        double salary
) {
}