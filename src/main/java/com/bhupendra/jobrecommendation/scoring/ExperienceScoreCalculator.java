package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;

public class ExperienceScoreCalculator {
    private static final double MAX_SCORE = 20.0;
    public double calculate(Candidate candidate, Job job) {

        int candidateExperience = candidate.getYearsOfExperience();
        int requiredExperience = job.getMinYearsExperience();

        if (candidateExperience >= requiredExperience) {
            return MAX_SCORE;
        }
        if (requiredExperience == 0) {
            return MAX_SCORE;
        }

        return ((double) candidateExperience / requiredExperience) * MAX_SCORE;
    }
}