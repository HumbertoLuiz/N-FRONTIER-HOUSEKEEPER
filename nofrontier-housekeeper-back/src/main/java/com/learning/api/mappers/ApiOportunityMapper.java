package com.learning.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import com.learning.api.dtos.responses.OportunityResponse;
import com.learning.api.dtos.responses.RatingResponse;
import com.learning.core.models.Daily;
import com.learning.core.models.Job;
import com.learning.core.repository.JobRepository;
import com.learning.core.repository.RatingRepository;


@Mapper(componentModel = "spring", uses = ApiUserDailyMapper.class)
public abstract class ApiOportunityMapper {

    @Autowired
    protected JobRepository jobRepository;

    @Autowired
    protected RatingRepository ratingRepository;

    @Autowired
    protected ApiRatingMapper ratingMapper;

    public static final ApiOportunityMapper INSTANCE = Mappers.getMapper(ApiOportunityMapper.class);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "jobName", source = "job.name")
    @Mapping(target = "job", source = "job.id")
    @Mapping(target = "ratingCustomer", source = "model")
    public abstract OportunityResponse toResponse(Daily model);

    protected List<RatingResponse> mapRatingCustomer (Daily model) {
        var customer = model.getCustomer();
        return ratingRepository.getLastRatings(customer)
            .stream()
            .map(ratingMapper::toResponse)
            .toList();
    }

    protected Job longToJob(Long jobId) {
        return jobRepository.findById(jobId)
            .orElseThrow(IllegalArgumentException::new);
    }


}
