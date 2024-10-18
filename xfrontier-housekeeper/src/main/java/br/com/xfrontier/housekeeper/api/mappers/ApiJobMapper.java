package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import br.com.xfrontier.housekeeper.api.dtos.requests.JobRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.JobResponse;
import br.com.xfrontier.housekeeper.core.models.Job;

@Mapper(componentModel = "spring")
public interface ApiJobMapper {

	ApiJobMapper INSTANCE = Mappers.getMapper(ApiJobMapper.class);

    Job toModel(JobRequest request);

		JobResponse toResponse(Job model);
}
