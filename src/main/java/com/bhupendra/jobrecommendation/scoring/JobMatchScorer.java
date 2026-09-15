package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.entity.SkillRequirement;
import com.bhupendra.jobrecommendation.entity.SkillType;
import org.springframework.stereotype.Component;

@Component
public class JobMatchScorer {
    private final SkillScoreCalculator skillScoreCalculator;
    private final ExperienceScoreCalculator experienceScoreCalculator;
    private final LocationScoreCalculator locationScoreCalculator;
    private final SalaryScoreCalculator salaryScoreCalculator;

    public JobMatchScorer(
            SkillScoreCalculator skillScoreCalculator,
            ExperienceScoreCalculator experienceScoreCalculator,
            LocationScoreCalculator locationScoreCalculator,
            SalaryScoreCalculator salaryScoreCalculator) {

        this.skillScoreCalculator = skillScoreCalculator;
        this.experienceScoreCalculator = experienceScoreCalculator;
        this.locationScoreCalculator = locationScoreCalculator;
        this.salaryScoreCalculator = salaryScoreCalculator;
    }

    public JobMatchResult calculate(Candidate candidate, Job job) {

        // Step 1: Check mandatory skills
        for (SkillRequirement requirement : job.getRequiredSkills()) {
            if (requirement.getType() == SkillType.MUST_HAVE) {
                if (!candidate.getSkills().contains(requirement.getSkill())) {
                    return null;
                }
            }
        }

        // Step 2: Calculate individual scores
        double skillScore = skillScoreCalculator.calculate(candidate, job);
        double experienceScore = experienceScoreCalculator.calculate(candidate, job);
        double locationScore = locationScoreCalculator.calculate(candidate, job);
        double salaryScore = salaryScoreCalculator.calculate(candidate, job);

        // Step 3: Total score
//      return skillScore + experienceScore + locationScore + salaryScore;
        return new JobMatchResult(
                skillScore,
                experienceScore,
                locationScore,
                salaryScore
        );
    }
}
