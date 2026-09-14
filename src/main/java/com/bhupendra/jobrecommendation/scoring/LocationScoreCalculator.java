package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;

public class LocationScoreCalculator {

    private static final double EXACT_MATCH_SCORE = 15.0;
    private static final double REMOTE_SCORE = 10.0;
    private static final double MISMATCH_SCORE = 0.0;

    public double calculate(Candidate candidate, Job job) {

        String candidateLocation = candidate.getLocation();
        String jobLocation = job.getLocation();

        if (candidateLocation.equalsIgnoreCase(jobLocation)) {
            return EXACT_MATCH_SCORE;
        }

        if (job.isRemoteAllowed()) {
            return REMOTE_SCORE;
        }

        return MISMATCH_SCORE;
    }
}