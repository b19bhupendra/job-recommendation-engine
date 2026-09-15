package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.entity.SalaryRange;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaryScoreCalculatorTest {

    private final SalaryScoreCalculator salaryScoreCalculator =
            new SalaryScoreCalculator();

    @Test
    void shouldReturnZeroWhenJobSalaryIsBelowExpectedSalary() {

        Candidate candidate = new Candidate(
                null,
                "John Doe",
                null,
                4,
                "Gurgaon",
                new BigDecimal("2000000")
        );

        Job job = new Job(
                null,
                "Java Developer",
                null,
                3,
                "Gurgaon",
                new SalaryRange(
                        new BigDecimal("1000000"),
                        new BigDecimal("1800000")
                ),
                true
        );

        double score =
                salaryScoreCalculator.calculate(candidate, job);

        assertEquals(0.0, score);
    }
}