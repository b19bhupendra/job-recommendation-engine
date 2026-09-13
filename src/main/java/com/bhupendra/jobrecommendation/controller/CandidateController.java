package com.bhupendra.jobrecommendation.controller;

import com.bhupendra.jobrecommendation.dto.request.CreateCandidateRequest;
import com.bhupendra.jobrecommendation.dto.response.CandidateResponse;
import com.bhupendra.jobrecommendation.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateResponse createCandidate(@Valid @RequestBody CreateCandidateRequest request){
        return candidateService.createCandidate(request);
    }
}
