package com.learning.web.services;

import java.util.List;
import com.learning.core.models.Job;
import com.learning.web.dtos.JobForm;

public interface WebJobService {
 
	List<Job> findAll();

	Job insert(JobForm form);

	Job findById(Long id);

	Job update(JobForm form, Long id);

	void deleteById(Long id);

}