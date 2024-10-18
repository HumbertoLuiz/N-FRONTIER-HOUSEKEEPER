package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.xfrontier.housekeeper.api.dtos.responses.CityAttendedResponse;
import br.com.xfrontier.housekeeper.core.models.CityAttended;

@Mapper(componentModel = "spring")
public interface ApiCityAttendedMapper {

	ApiCityAttendedMapper INSTANCE = Mappers.getMapper(ApiCityAttendedMapper.class);

	CityAttendedResponse toResponse(CityAttended model);

}
