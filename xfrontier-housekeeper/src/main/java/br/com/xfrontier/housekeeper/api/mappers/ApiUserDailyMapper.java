package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.xfrontier.housekeeper.api.dtos.responses.UserDailyResponse;
import br.com.xfrontier.housekeeper.core.models.User;

@Mapper(componentModel = "spring")
public interface ApiUserDailyMapper {

	ApiUserDailyMapper INSTANCE = Mappers.getMapper(ApiUserDailyMapper.class);

    @Mapping(target = "userType", source = "userType.id")
    @Mapping(target = "userPicture", source = "userPicture.url")
    UserDailyResponse toResponse(User model);

}
