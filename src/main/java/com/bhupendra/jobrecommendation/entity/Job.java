package com.bhupendra.jobrecommendation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "jobs")
public class Job {

    @Id
    private String id;
    private String title;
    private List<SkillRequirement> requiredSkills;
    private int minYearsExperience;
    private String location;
    private SalaryRange salaryRange;
    private boolean remoteAllowed;
}
