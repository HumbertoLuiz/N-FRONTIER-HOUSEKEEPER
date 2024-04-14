package com.learning.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.learning.core.models.User;
import com.learning.web.dtos.UserInsertForm;
import com.learning.web.dtos.UserUpdateForm;

@Mapper(componentModel = "spring")
public interface WebUserMapper {

	WebUserMapper INSTANCE = Mappers.getMapper(WebUserMapper.class);

    User toModel(UserInsertForm form);

    User toModel(UserUpdateForm form);

    UserUpdateForm toForm(User model);    
    
}
