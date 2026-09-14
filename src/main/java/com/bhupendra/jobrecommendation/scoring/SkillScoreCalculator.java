package com.bhupendra.jobrecommendation.scoring;

import com.bhupendra.jobrecommendation.entity.Candidate;
import com.bhupendra.jobrecommendation.entity.Job;
import com.bhupendra.jobrecommendation.entity.SkillRequirement;
import com.bhupendra.jobrecommendation.entity.SkillType;

import java.util.List;

public class SkillScoreCalculator {
    private static final double MUST_HAVE_WEIGHT = 40.0;
    private static final double NICE_TO_HAVE_WEIGHT = 10.0;

    public double calculate(Candidate candidate, Job job){
        List<String> candidateSkills = candidate.getSkills();
        List<SkillRequirement> requiredSkills = job.getRequiredSkills();

        int mustHaveTotal = 0;
        int mustHaveMatched = 0;

        int niceToHaveTotal = 0;
        int niceToHaveMatched = 0;
        //going through every skill required by the job.
        for(SkillRequirement requirement : requiredSkills){
            //How many MUST_HAVE skills does this job have?
            if(requirement.getType() == SkillType.MUST_HAVE){
                mustHaveTotal++;
                //How many of those does the candidate have?
                if(candidateSkills.contains(requirement.getSkill())){
                    mustHaveMatched++;
                }
            }else if(requirement.getType() == SkillType.NICE_TO_HAVE){
                niceToHaveTotal++;
                if(candidateSkills.contains(requirement.getSkill())){
                    niceToHaveMatched++;
                }
            }
        }

        double mustHaveScore = 0;
        if(mustHaveTotal > 0){
            mustHaveScore = ((double) mustHaveMatched / mustHaveTotal) * MUST_HAVE_WEIGHT;
        }
        double niceToHaveScore = 0;
        if(niceToHaveTotal > 0){
                niceToHaveScore = ((double) niceToHaveMatched / niceToHaveTotal) * NICE_TO_HAVE_WEIGHT;
        }
        return mustHaveScore + niceToHaveScore;
    }
}
