package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.xfrontier.housekeeper.api.dtos.requests.RatingRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.RatingResponse;
import br.com.xfrontier.housekeeper.core.models.Rating;

@Mapper(componentModel = "spring")
public interface ApiRatingMapper {

    ApiRatingMapper INSTANCE = Mappers.getMapper(ApiRatingMapper.class);

    Rating toModel(RatingRequest request);

    @Mapping(target = "evaluatorName", source = "evaluator.completeName")
    @Mapping(target = "evaluatorPicture", source = "evaluator.userPicture.url")
    RatingResponse toResponse(Rating model);

}
