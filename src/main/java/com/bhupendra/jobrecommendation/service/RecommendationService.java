package com.bhupendra.jobrecommendation.service;

import com.bhupendra.jobrecommendation.dto.response.JobRecommendationResponse;
import com.bhupendra.jobrecommendation.dto.response.MatchBreakdown;
import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.repository.CandidateRepository;
import com.bhupendra.jobrecommendation.repository.JobRepository;
import com.bhupendra.jobrecommendation.scoring.JobMatchResult;
import com.bhupendra.jobrecommendation.scoring.JobMatchScorer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendationService {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final JobMatchScorer jobMatchScorer;

    public RecommendationService(
            CandidateRepository candidateRepository,
            JobRepository jobRepository,
            JobMatchScorer jobMatchScorer) {

        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.jobMatchScorer = jobMatchScorer;
    }

    public List<JobRecommendationResponse> getRecommendations(
            String candidateId,
            int limit) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new RuntimeException("Candidate not found"));

        List<JobRecommendationResponse> recommendations =
                new ArrayList<>();

        List<Job> jobs = jobRepository.findAll();
        //System.out.println("Number of jobs found: " + jobs.size());

        for (Job job : jobs) {

            JobMatchResult result =
                    jobMatchScorer.calculate(candidate, job);

            // Missing MUST_HAVE skill → exclude job
            if (result == null) {
                continue;
            }

            MatchBreakdown breakdown = new MatchBreakdown(
                    result.skillScore(),
                    result.experienceScore(),
                    result.locationScore(),
                    result.salaryScore()
            );

            JobRecommendationResponse response =
                    new JobRecommendationResponse(
                            job.getId(),
                            job.getTitle(),
                            result.totalScore(),
                            breakdown
                    );

            recommendations.add(response);
        }

        // Highest score first
        recommendations.sort(
                Comparator.comparingDouble(
                        JobRecommendationResponse::score
                ).reversed()
        );

        // Apply limit
        if (limit < recommendations.size()) {
            return recommendations.subList(0, limit);
        }

        return recommendations;
    }
}