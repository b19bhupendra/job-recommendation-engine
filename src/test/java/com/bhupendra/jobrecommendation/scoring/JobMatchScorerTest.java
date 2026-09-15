package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.entity.SalaryRange;
import com.bhupendra.jobrecommendation.entity.SkillRequirement;
import com.bhupendra.jobrecommendation.entity.SkillType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

class JobMatchScorerTest {

    private final SkillScoreCalculator skillScoreCalculator =
            new SkillScoreCalculator();

    private final ExperienceScoreCalculator experienceScoreCalculator =
            new ExperienceScoreCalculator();

    private final LocationScoreCalculator locationScoreCalculator =
            new LocationScoreCalculator();

    private final SalaryScoreCalculator salaryScoreCalculator =
            new SalaryScoreCalculator();

    private final JobMatchScorer jobMatchScorer =
            new JobMatchScorer(
                    skillScoreCalculator,
                    experienceScoreCalculator,
                    locationScoreCalculator,
                    salaryScoreCalculator
            );

    @Test
    void shouldExcludeJobWhenMandatorySkillIsMissing() {

        // Candidate does not have MongoDB
        Candidate candidate = new Candidate(
                null,
                "John Doe",
                List.of("Java", "Spring Boot"),
                4,
                "Gurgaon",
                new BigDecimal("1200000")
        );

        // MongoDB is a MUST_HAVE skill
        Job job = new Job(
                null,
                "Senior Java Developer",
                List.of(
                        new SkillRequirement(
                                "Java",
                                SkillType.MUST_HAVE
                        ),
                        new SkillRequirement(
                                "Spring Boot",
                                SkillType.MUST_HAVE
                        ),
                        new SkillRequirement(
                                "MongoDB",
                                SkillType.MUST_HAVE
                        )
                ),
                3,
                "Gurgaon",
                new SalaryRange(
                        new BigDecimal("1000000"),
                        new BigDecimal("1800000")
                ),
                true
        );

        JobMatchResult result =
                jobMatchScorer.calculate(candidate, job);

        // Job should be excluded because
        // candidate is missing a MUST_HAVE skill
        assertNull(result);
    }
}