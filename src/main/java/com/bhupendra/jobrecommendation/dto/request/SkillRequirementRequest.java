package com.bhupendra.jobrecommendation.dto.request;

import com.bhupendra.jobrecommendation.entity.SkillType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SkillRequirementRequest(
        @NotBlank(message = "Skill is required")
        String skill,
        @NotNull(message = "Skill type is required")
        SkillType type
) {
}
