package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.responses.UserResponse;
import br.com.xfrontier.housekeeper.api.services.ApiMeService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

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
