package com.learning.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.assembler.DailyAssembler;
import com.learning.api.dtos.requests.DailyRequest;
import com.learning.api.dtos.responses.DailyResponse;
import com.learning.api.services.ApiDailyService;
import com.learning.core.permissions.EHousekeeperPermissions;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/diarias")
public class DailyRestController {

    @Autowired
    private ApiDailyService service;

    @Autowired
    private DailyAssembler assembler;

    @EHousekeeperPermissions.isHousekeeperOrCustomer
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DailyResponse register(@RequestBody @Valid DailyRequest request) {
        var response = service.register(request);

        assembler.addLinks(response);

        return response;
    }

    @EHousekeeperPermissions.isHousekeeperOrCustomer
    @GetMapping
    public List<DailyResponse> listByLoggedUser() {
        var response = service.listByLoggedUser();

        assembler.addLinks(response);

        return response;
    }

    @EHousekeeperPermissions.isHousekeeperOrCustomer
    @GetMapping("/{id}")
    public DailyResponse findById(@PathVariable Long id) {
        var response = service.findById(id);

        assembler.addLinks(response);

        return response;
    }

}
