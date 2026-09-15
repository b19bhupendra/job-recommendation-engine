package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalaryScoreCalculator {
    private static final double MAX_SCORE = 15.0;

    public double calculate(Candidate candidate, Job job) {

        BigDecimal expectedSalary = candidate.getExpectedSalary();
        BigDecimal salaryMin = job.getSalaryRange().getMin();
        BigDecimal salaryMax = job.getSalaryRange().getMax();

        // Expected salary is inside the job's salary range
        if (expectedSalary.compareTo(salaryMin) >= 0
                && expectedSalary.compareTo(salaryMax) <= 0) {

            return MAX_SCORE;
        }

        // Job's minimum salary is above candidate's expectation
        if (salaryMin.compareTo(expectedSalary) > 0) {
            return MAX_SCORE;
        }

        // Job's maximum salary is below candidate's expectation
        if (salaryMax.compareTo(expectedSalary) < 0) {
            return 0.0;
        }
        return 0.0;
    }
}