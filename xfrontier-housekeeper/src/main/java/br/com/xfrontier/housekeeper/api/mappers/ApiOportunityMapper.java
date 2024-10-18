package br.com.xfrontier.housekeeper.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xfrontier.housekeeper.api.dtos.responses.OportunityResponse;
import br.com.xfrontier.housekeeper.api.dtos.responses.RatingResponse;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.Job;
import br.com.xfrontier.housekeeper.core.repository.JobRepository;
import br.com.xfrontier.housekeeper.core.repository.RatingRepository;


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
