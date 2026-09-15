package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.entity.SalaryRange;
import com.bhupendra.jobrecommendation.entity.SkillRequirement;
import com.bhupendra.jobrecommendation.entity.SkillType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JobMatchScorerSuccessTest {

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
    void shouldCalculateCorrectScoreForGoodMatch() {

        Candidate candidate = new Candidate(
                null,
                "John Doe",
                List.of("Java", "Spring Boot", "MongoDB"),
                4,
                "Gurgaon",
                new BigDecimal("1200000")
        );

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
                                SkillType.NICE_TO_HAVE
                        ),
                        new SkillRequirement(
                                "Docker",
                                SkillType.NICE_TO_HAVE
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

        assertNotNull(result);

        assertEquals(45.0, result.skillScore());
        assertEquals(20.0, result.experienceScore());
        assertEquals(15.0, result.locationScore());
        assertEquals(15.0, result.salaryScore());

        assertEquals(95.0, result.totalScore());
    }
}