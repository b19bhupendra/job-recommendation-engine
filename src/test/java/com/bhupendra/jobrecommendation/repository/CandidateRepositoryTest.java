package com.bhupendra.jobrecommendation.repository;

import com.bhupendra.jobrecommendation.entity.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    void shouldSaveAndFindCandidate(){
        Candidate candidate = new Candidate(
                null,
                "John Doe",
                List.of("Java", "Spring Boot", "MongoDB"),
                4,
                "Gurgaon",
                new BigDecimal("1200000")
        );

        Candidate savedCandidate = candidateRepository.save(candidate);

        assertThat(savedCandidate.getId()).isNotNull();

        Candidate foundCandidate = candidateRepository
                .findById(savedCandidate.getId())
                .orElseThrow();

        assertThat(foundCandidate.getName()).isEqualTo("John Doe");
        assertThat(foundCandidate.getYearsOfExperience()).isEqualTo(4);
        assertThat(foundCandidate.getSkills())
                .containsExactly("Java", "Spring Boot", "MongoDB");

        candidateRepository.deleteById(savedCandidate.getId());
    }
}
