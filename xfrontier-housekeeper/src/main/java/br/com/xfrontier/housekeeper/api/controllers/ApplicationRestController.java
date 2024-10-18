package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.services.ApiApplicationService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;


@RestController
@RequestMapping("/api/daily/{id}/apply")
public class ApplicationRestController {

    @Autowired
    private ApiApplicationService service;

    @PostMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse apply(@PathVariable Long id) {
        return service.apply(id);
    }

}
