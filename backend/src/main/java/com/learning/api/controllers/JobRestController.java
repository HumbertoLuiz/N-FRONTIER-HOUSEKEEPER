package com.learning.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.api.dtos.responses.JobResponse;
import com.learning.api.services.ApiJobService;


@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

	@Autowired
	private ApiJobService service;

	@GetMapping
	public List<JobResponse> findAll() {
		return service.findAll();
	}
}
