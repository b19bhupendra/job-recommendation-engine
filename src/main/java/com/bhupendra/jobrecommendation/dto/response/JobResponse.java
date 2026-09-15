package com.bhupendra.jobrecommendation.dto.response;

import com.bhupendra.jobrecommendation.entity.SkillRequirement;

import java.math.BigDecimal;
import java.util.List;

public record JobResponse(
        String id,
        String title,
        List<SkillRequirement> requiredSkills,
        int minYearsExperience,
        String location,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        boolean remoteAllowed
) {
}
