package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.requests.RatingRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.services.ApiRatingService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/daily/{id}/rating")
public class RatingRestController {

    @Autowired
    private ApiRatingService service;

    @PatchMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public MessageResponse rateDaily(
        @RequestBody @Valid RatingRequest request, @PathVariable Long id
    ) {
        return service.rateDaily(request, id);
    }

}
