package br.com.xfrontier.housekeeper.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.xfrontier.housekeeper.api.dtos.responses.JobResponse;
import br.com.xfrontier.housekeeper.api.services.ApiJobService;


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
