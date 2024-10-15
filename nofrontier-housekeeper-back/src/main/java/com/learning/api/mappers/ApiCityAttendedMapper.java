package com.learning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.learning.api.dtos.responses.CityAttendedResponse;
import com.learning.core.models.CityAttended;

@Mapper(componentModel = "spring")
public interface ApiCityAttendedMapper {

	ApiCityAttendedMapper INSTANCE = Mappers.getMapper(ApiCityAttendedMapper.class);

	CityAttendedResponse toResponse(CityAttended model);

}
