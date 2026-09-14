package com.bhupendra.jobrecommendation.scoring;

public record JobMatchResult(
        double skillScore,
        double experienceScore,
        double locationScore,
        double salaryScore
) {

    public double totalScore() {
        return skillScore
                + experienceScore
                + locationScore
                + salaryScore;
    }
}