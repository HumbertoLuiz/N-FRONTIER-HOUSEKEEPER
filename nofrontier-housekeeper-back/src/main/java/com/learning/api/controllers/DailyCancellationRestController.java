package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.requests.DailyCancellationRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.services.ApiDailyCancellationService;
import com.learning.core.permissions.EHousekeeperPermissions;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/daily/{id}/cancel")
public class DailyCancellationRestController {

    @Autowired
    private ApiDailyCancellationService service;

    @PatchMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse cancel(
        @PathVariable Long id,
        @RequestBody @Valid DailyCancellationRequest request
    ) {
        return service.cancel(id, request);
    }

}
