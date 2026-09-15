package com.bhupendra.jobrecommendation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRange {

    private BigDecimal min;
    private BigDecimal max;
}
