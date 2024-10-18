package br.com.xfrontier.housekeeper.web.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import br.com.xfrontier.housekeeper.core.models.Job;
import br.com.xfrontier.housekeeper.web.dtos.JobForm;

@Mapper(componentModel = "spring")
public interface WebJobMapper {

	WebJobMapper INSTANCE = Mappers.getMapper(WebJobMapper.class);

    Job toModel(JobForm form);

    JobForm toForm(Job model);
    
    
    
}
