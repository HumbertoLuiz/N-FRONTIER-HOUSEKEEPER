package com.learning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.learning.api.dtos.responses.UserDailyResponse;
import com.learning.core.models.User;

@Mapper(componentModel = "spring")
public interface ApiUserDailyMapper {

	ApiUserDailyMapper INSTANCE = Mappers.getMapper(ApiUserDailyMapper.class);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    UserDailyResponse toResponse(User model);

}
