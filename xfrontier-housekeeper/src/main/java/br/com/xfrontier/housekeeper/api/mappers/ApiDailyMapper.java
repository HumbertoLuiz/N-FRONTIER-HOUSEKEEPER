package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xfrontier.housekeeper.api.dtos.requests.DailyRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.DailyResponse;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.Job;
import br.com.xfrontier.housekeeper.core.repository.JobRepository;

@Mapper(componentModel = "spring", uses = ApiUserDailyMapper.class)
public abstract class ApiDailyMapper {

    @Autowired
    protected JobRepository jobRepository;

    public static final ApiDailyMapper INSTANCE = Mappers.getMapper(ApiDailyMapper.class);

    public abstract Daily toModel(DailyRequest request);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "jobName", source = "job.name")
    @Mapping(target = "job", source = "job.id")
    public abstract DailyResponse toResponse(Daily model);

    protected Job longToJob(Long jobId) {
        return jobRepository.findById(jobId)
            .orElseThrow(IllegalArgumentException::new);
    }

}
