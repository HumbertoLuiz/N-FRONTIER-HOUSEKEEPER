package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.responses.UserResponse;
import com.learning.api.mappers.ApiUserMapper;
import com.learning.core.utils.SecurityUtils;

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
