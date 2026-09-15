package com.bhupendra.jobrecommendation.controller;

import com.bhupendra.jobrecommendation.dto.request.CreateJobRequest;
import com.bhupendra.jobrecommendation.dto.response.JobResponse;
import com.bhupendra.jobrecommendation.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponse createJob(@Valid @RequestBody CreateJobRequest request){
        return jobService.createJob(request);
    }
}
