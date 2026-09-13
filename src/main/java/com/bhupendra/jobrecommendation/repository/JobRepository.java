package com.bhupendra.jobrecommendation.repository;

import com.bhupendra.jobrecommendation.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String> {

}
