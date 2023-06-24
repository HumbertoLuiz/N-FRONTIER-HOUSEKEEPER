package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.responses.UserResponse;
import com.learning.api.services.ApiMeService;
import com.learning.core.permissions.EHousekeeperPermissions;

@RestController
@RequestMapping("/api/me")
public class MeRestController {

    @Autowired
    private ApiMeService apiMeService;

    @EHousekeeperPermissions.isHousekeeperOrCustomer
    @GetMapping
    public UserResponse me() {
        return apiMeService.getLoggedUser();
    }
}
