package com.learning.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.learning.core.models.Job;
import com.learning.web.dtos.JobForm;

@Mapper(componentModel = "spring")
public interface WebJobMapper {

	WebJobMapper INSTANCE = Mappers.getMapper(WebJobMapper.class);

    Job toModel(JobForm form);

    JobForm toForm(Job model);
    
    
    
}
