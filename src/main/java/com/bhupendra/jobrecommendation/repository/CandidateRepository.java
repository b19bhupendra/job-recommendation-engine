package com.bhupendra.jobrecommendation.repository;

import com.bhupendra.jobrecommendation.entity.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
}
