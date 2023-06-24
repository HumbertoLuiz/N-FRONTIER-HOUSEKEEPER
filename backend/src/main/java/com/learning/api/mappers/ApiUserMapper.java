package com.learning.api.mappers;

import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.learning.api.dtos.requests.UserRequest;
import com.learning.api.dtos.responses.UserRegisterResponse;
import com.learning.api.dtos.responses.UserResponse;
import com.learning.core.enums.UserType;
import com.learning.core.models.User;

@Mapper(componentModel = "spring")
public interface ApiUserMapper {

    ApiUserMapper INSTANCE= Mappers.getMapper(ApiUserMapper.class);

    @Mapping(target= "password", source= "password")
    @Mapping(target= "documentPicture", ignore= true)
    User toModel(UserRequest request);

    @Mapping(target= "userType", source= "userType.id")
    @Mapping(target= "userPicture", source= "userPicture.url")
    UserResponse toResponse(User model);

    @Mapping(target= "userType", source= "userType.id")
    @Mapping(target= "userPicture", source= "userPicture.url")
    UserRegisterResponse toRegisterResponse(User model);

    default UserType integerToUserType(Integer value) {
        return Stream.of(UserType.values())
            .filter(userType -> userType.getId().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("User Type not valid"));
    }

}
