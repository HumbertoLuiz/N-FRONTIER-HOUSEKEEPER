package br.com.xfrontier.housekeeper.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.requests.RefreshRequest;
import br.com.xfrontier.housekeeper.api.dtos.requests.TokenRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.TokenResponse;
import br.com.xfrontier.housekeeper.api.services.ApiAuthService;

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

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid RefreshRequest refreshRequest) {
        apiAuthService.logout(refreshRequest);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
