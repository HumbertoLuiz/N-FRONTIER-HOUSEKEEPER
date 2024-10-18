package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.services.ApiConfirmationPresenceService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

@RestController
@RequestMapping("/api/daily/{id}/confirm-presence")
public class ConfirmationPresenceRestController {

    @Autowired
    private ApiConfirmationPresenceService service;

    @PatchMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse confirmPresence(@PathVariable Long id) {
        return service.confirmPresence(id);
    }

}
