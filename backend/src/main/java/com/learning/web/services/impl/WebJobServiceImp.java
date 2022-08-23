package com.learning.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.core.exceptions.ServiceNotFoundException;
import com.learning.core.models.Job;
import com.learning.core.repository.JobRepository;
import com.learning.web.dtos.JobForm;
import com.learning.web.mappers.WebJobMapper;
import com.learning.web.services.WebJobService;

@Service
public class WebJobServiceImp implements WebJobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private WebJobMapper webJobMapper;

	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	public Job insert(JobForm form) {
		var model = webJobMapper.toModel(form);

		return jobRepository.save(model);
	}

	public Job findById(Long id) {
		var jobFound = jobRepository.findById(id);

		if (jobFound.isPresent()) {
			return jobFound.get();
		}

		var message = String.format("Service with ID %d not found", id);
		throw new ServiceNotFoundException(message);
	}

	public Job update(JobForm form, Long id) {
		var jobFound = findById(id);

		var model = webJobMapper.toModel(form);
		((Job) model).setId(jobFound.getId());

		return jobRepository.save(model);
	}

	public void deleteById(Long id) {
		var jobFound = findById(id);

		jobRepository.delete(jobFound);
	}
}