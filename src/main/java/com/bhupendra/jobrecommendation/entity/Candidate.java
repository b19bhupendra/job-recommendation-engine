package com.bhupendra.jobrecommendation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Document(collection = "candidates")
public class Candidate {

    @Id
    private String id;

    private String name;
    private List<String> skills;
    private int yearsOfExperience;
    private String location;
    private BigDecimal expectedSalary;
}
