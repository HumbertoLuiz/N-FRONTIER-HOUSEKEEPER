package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.services.ApiConfirmationPresenceService;
import com.learning.core.permissions.EHousekeeperPermissions;

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
