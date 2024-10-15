package com.learning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.learning.api.dtos.requests.RatingRequest;
import com.learning.api.dtos.responses.RatingResponse;
import com.learning.core.models.Rating;

@Mapper(componentModel = "spring")
public interface ApiRatingMapper {

    ApiRatingMapper INSTANCE = Mappers.getMapper(ApiRatingMapper.class);

    Rating toModel(RatingRequest request);

    @Mapping(target = "evaluatorName", source = "evaluator.completName")
    @Mapping(target = "evaluatorPicture", source = "evaluator.userPicture.url")
    RatingResponse toResponse(Rating model);

}
