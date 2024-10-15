package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.responses.AvailabilityResponse;
import com.learning.api.dtos.responses.HousekeeperLocationPagedResponse;
import com.learning.api.services.ApiHousekeeperService;

@RestController
@RequestMapping("/api/housekeeper")
public class HousekeeperRestController {

    @Autowired
    private ApiHousekeeperService service;

    @GetMapping("/location")
    public HousekeeperLocationPagedResponse findHousekeeperByzipCode(
        @RequestParam(required= false) String zipCode) {
        return service.findHousekeeperByzipCode(zipCode);
    }

    @GetMapping("/availability")
    public AvailabilityResponse checkAvailabilityByzipCode(@RequestParam(required= false) String zipCode) {
        return service.checkAvailabilityByzipCode(zipCode);
    }

}
