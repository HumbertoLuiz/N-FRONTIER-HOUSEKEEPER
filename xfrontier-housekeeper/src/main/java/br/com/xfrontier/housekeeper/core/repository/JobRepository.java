package br.com.xfrontier.housekeeper.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.xfrontier.housekeeper.core.models.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
