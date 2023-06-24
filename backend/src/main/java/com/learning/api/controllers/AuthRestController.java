package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.requests.RefreshRequest;
import com.learning.api.dtos.requests.TokenRequest;
import com.learning.api.dtos.responses.TokenResponse;
import com.learning.api.services.ApiAuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private ApiAuthService apiAuthService;

    @PostMapping("/token")
    public TokenResponse authenticate(@RequestBody @Valid TokenRequest toquenRequest) {
        return apiAuthService.authenticate(toquenRequest);
    }

    @PostMapping("/refresh")
    public TokenResponse reauthenticate(@RequestBody @Valid RefreshRequest refreshRequest) {
        return apiAuthService.reauthenticate(refreshRequest);
    }
}
