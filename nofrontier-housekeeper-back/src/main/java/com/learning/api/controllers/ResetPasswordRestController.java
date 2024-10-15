package com.learning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.requests.ResetPasswordConfirmationRequest;
import com.learning.api.dtos.requests.ResetPasswordRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.services.ApiResetPasswordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recover-password")
public class ResetPasswordRestController {

    @Autowired
    private ApiResetPasswordService service;

    @PostMapping
    public MessageResponse solictarResetDeSenha(@RequestBody @Valid ResetPasswordRequest request) {
        return service.requestPasswordReset(request);
    }

    @PostMapping("/confirm")
    public MessageResponse confirmResetPassword(@RequestBody @Valid ResetPasswordConfirmationRequest request) {
        return service.confirmPasswordReset(request);
    }

}
