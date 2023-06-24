package com.learning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.learning.api.dtos.requests.JobRequest;
import com.learning.api.dtos.responses.JobResponse;
import com.learning.core.models.Job;

@Mapper(componentModel = "spring")
public interface ApiJobMapper {

	ApiJobMapper INSTANCE = Mappers.getMapper(ApiJobMapper.class);

    Job toModel(JobRequest request);

		JobResponse toResponse(Job model);
}
