package com.bhupendra.jobrecommendation.service;

import com.bhupendra.jobrecommendation.dto.request.CreateCandidateRequest;
import com.bhupendra.jobrecommendation.dto.response.CandidateResponse;
import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public CandidateResponse createCandidate(CreateCandidateRequest request){

        Candidate candidate = new Candidate(
                null,
                request.name(),
                request.skills(),
                request.yearsOfExperience(),
                request.location(),
                request.expectedSalary()
        );

        Candidate savedCandidate = candidateRepository.save(candidate);

        return new CandidateResponse(
                savedCandidate.getId(),
                savedCandidate.getName(),
                savedCandidate.getSkills(),
                savedCandidate.getYearsOfExperience(),
                savedCandidate.getLocation(),
                savedCandidate.getExpectedSalary()
        );
    }
}
