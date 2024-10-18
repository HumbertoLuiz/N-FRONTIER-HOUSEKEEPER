package br.com.xfrontier.housekeeper.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.xfrontier.housekeeper.api.dtos.responses.HousekeeperLocationResponse;
import br.com.xfrontier.housekeeper.core.models.User;

@Mapper(componentModel= "spring")
public interface ApiHousekeeperMapper {

    ApiHousekeeperMapper INSTANCE= Mappers.getMapper(ApiHousekeeperMapper.class);

    @Mapping(target= "userPicture", source= "userPicture.url")
    HousekeeperLocationResponse toHousekeeperLocationResponse(User model);

}
