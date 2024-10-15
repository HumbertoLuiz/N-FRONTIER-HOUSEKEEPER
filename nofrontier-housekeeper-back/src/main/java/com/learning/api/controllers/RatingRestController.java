package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.requests.RatingRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.services.ApiRatingService;
import com.learning.core.permissions.EHousekeeperPermissions;

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
