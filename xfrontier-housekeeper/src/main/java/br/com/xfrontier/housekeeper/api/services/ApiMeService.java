package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.responses.UserResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiUserMapper;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;

@Service
public class ApiMeService {

    @Autowired
    private ApiUserMapper userMapper;

    @Autowired
    private SecurityUtils securityUtils;

    public UserResponse getLoggedUser() {
        var userLogged= securityUtils.getLoggedUser();
        return userMapper.toResponse(userLogged);
    }

}
