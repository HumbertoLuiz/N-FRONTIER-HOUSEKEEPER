package com.learning.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.core.models.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
