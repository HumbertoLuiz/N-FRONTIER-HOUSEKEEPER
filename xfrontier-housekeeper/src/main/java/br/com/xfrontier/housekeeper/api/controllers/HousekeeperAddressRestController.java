package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.requests.HousekeeperAddressRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.HousekeeperAddressResponse;
import br.com.xfrontier.housekeeper.api.services.ApiHousekeeperAddressService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/address")
public class HousekeeperAddressRestController {

    @Autowired
    private ApiHousekeeperAddressService service;

    @PutMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public HousekeeperAddressResponse updateAddress(@RequestBody @Valid HousekeeperAddressRequest request) {
        return service.updateAddress(request);
    }

    @GetMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public HousekeeperAddressResponse showAddress() {
        return service.showAddress();
    }

}
