package com.learning.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.assembler.OportunityAssembler;
import com.learning.api.dtos.responses.OportunityResponse;
import com.learning.api.services.ApiOportunityService;
import com.learning.core.permissions.EHousekeeperPermissions;

@RestController
@RequestMapping("/api/oportunidades")
public class OportunityRestController {

    @Autowired
    private ApiOportunityService service;

    @Autowired
    private OportunityAssembler assembler;

    @GetMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public List<OportunityResponse> findOportunities() {
        var response = service.findOportunities();
        assembler.addLinks(response);
        return response;
    }

}
