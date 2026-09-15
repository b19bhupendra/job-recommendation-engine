package com.bhupendra.jobrecommendation.service;

import com.bhupendra.jobrecommendation.dto.request.CreateJobRequest;
import com.bhupendra.jobrecommendation.dto.request.SalaryRangeRequest;
import com.bhupendra.jobrecommendation.dto.request.SkillRequirementRequest;
import com.bhupendra.jobrecommendation.dto.response.JobResponse;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.entity.SalaryRange;
import com.bhupendra.jobrecommendation.entity.SkillRequirement;
import com.bhupendra.jobrecommendation.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public JobResponse createJob(CreateJobRequest request) {

        List<SkillRequirement> requiredSkills = request.requiredSkills()
                .stream()
                .map(this::toSkillRequirement)
                .toList();

        SalaryRange salaryRange = toSalaryRange(request.salaryRange());

        Job job = new Job(
                null,
                request.title(),
                requiredSkills,
                request.minYearsExperience(),
                request.location(),
                salaryRange,
                request.remoteAllowed()
        );

        Job savedJob = jobRepository.save(job);

        return new JobResponse(
                savedJob.getId(),
                savedJob.getTitle(),
                savedJob.getRequiredSkills(),
                savedJob.getMinYearsExperience(),
                savedJob.getLocation(),
                savedJob.getSalaryRange().getMin(),
                savedJob.getSalaryRange().getMax(),
                savedJob.isRemoteAllowed()
        );
    }


    private SkillRequirement toSkillRequirement(SkillRequirementRequest request){
        return new SkillRequirement(
                request.skill(),
                request.type()
        );
    }

    private SalaryRange toSalaryRange(SalaryRangeRequest request){
        return new SalaryRange(
                request.min(),
                request.max()
        );
    }
}
