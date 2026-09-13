package com.bhupendra.jobrecommendation.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public record CreateCandidateRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotEmpty(message = "Skills mmust not be empty")
        List<@NotBlank(message = "Skills must not be blank")String> skills,

        @Min(value = 0, message = "Year of experience cannot be negative")
        int yearsOfExperience,

        @NotBlank(message = "Location is required")
        String location,

        @DecimalMin(value = "0.0", inclusive = true, message = "Expected salary cannot be negative")
        BigDecimal expectedSalary
) {
}
