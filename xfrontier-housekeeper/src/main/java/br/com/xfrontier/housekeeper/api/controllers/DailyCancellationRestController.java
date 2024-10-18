package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.requests.DailyCancellationRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.services.ApiDailyCancellationService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

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
