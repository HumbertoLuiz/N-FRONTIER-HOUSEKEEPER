package br.com.xfrontier.housekeeper.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.assembler.OportunityAssembler;
import br.com.xfrontier.housekeeper.api.dtos.responses.OportunityResponse;
import br.com.xfrontier.housekeeper.api.services.ApiOportunityService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

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
