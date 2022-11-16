package com.learning.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.learning.api.dtos.response.HousekeeperLocationResponse;
import com.learning.core.models.User;


@Mapper(componentModel = "spring")
public interface ApiHousekeeperMapper {

    ApiHousekeeperMapper INSTANCE = Mappers.getMapper(ApiHousekeeperMapper.class);

    @Mapping(target = "userPicture", source = "userPicture.url")
    HousekeeperLocationResponse toHousekeeperLocationResponse(User model);

}