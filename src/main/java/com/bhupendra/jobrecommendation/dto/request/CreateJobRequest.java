package com.bhupendra.jobrecommendation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
public record CreateJobRequest(

        @NotBlank(message = "Job title is required")
        String title,

        @NotEmpty(message = "Required skills must not be empty")
        List<@Valid SkillRequirementRequest> requiredSkills,

        @Min(value = 0, message = "Minimum years of experience cannot be negative")
        int minYearsExperience,

        @NotBlank(message = "Location is required")
        String location,

        @NotNull(message = "Salary range is required")
        @Valid SalaryRangeRequest salaryRange,
        boolean remoteAllowed

) {
}
