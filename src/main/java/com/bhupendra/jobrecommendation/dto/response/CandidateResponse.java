package com.bhupendra.jobrecommendation.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record CandidateResponse(
        String id,
        String name,
        List<String> skills,
        int yearsOfExperience,
        String location,
        BigDecimal expectedSalary
) {
}
