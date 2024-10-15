package com.learning.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.learning.api.dtos.responses.JobResponse;
import com.learning.api.mappers.ApiJobMapper;
import com.learning.core.models.Job;
import com.learning.core.repository.JobRepository;

@Service
public class ApiJobService {

	@Autowired
	private JobRepository repository;

	@Autowired
	private ApiJobMapper mapper;


    public List<JobResponse> findAll() {

        var jobSort = Sort.sort(Job.class);
        var order = jobSort.by(Job::getPosition).ascending();

        return repository.findAll(order)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
