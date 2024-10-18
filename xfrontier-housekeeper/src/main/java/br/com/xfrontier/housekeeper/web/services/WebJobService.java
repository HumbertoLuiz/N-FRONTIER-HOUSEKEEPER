package br.com.xfrontier.housekeeper.web.services;

import java.util.List;
import br.com.xfrontier.housekeeper.core.models.Job;
import br.com.xfrontier.housekeeper.web.dtos.JobForm;

public interface WebJobService {
 
	List<Job> findAll();

	Job insert(JobForm form);

	Job findById(Long id);

	Job update(JobForm form, Long id);

	void deleteById(Long id);

}