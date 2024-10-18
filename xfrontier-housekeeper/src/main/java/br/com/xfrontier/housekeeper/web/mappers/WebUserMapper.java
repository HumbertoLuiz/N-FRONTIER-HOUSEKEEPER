package br.com.xfrontier.housekeeper.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.xfrontier.housekeeper.core.models.User;
import br.com.xfrontier.housekeeper.web.dtos.UserInsertForm;
import br.com.xfrontier.housekeeper.web.dtos.UserUpdateForm;

@Mapper(componentModel = "spring")
public interface WebUserMapper {

	WebUserMapper INSTANCE = Mappers.getMapper(WebUserMapper.class);

    User toModel(UserInsertForm form);

    User toModel(UserUpdateForm form);

    UserUpdateForm toForm(User model);    
    
}
