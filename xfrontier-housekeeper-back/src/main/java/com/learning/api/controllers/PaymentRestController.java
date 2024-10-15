package com.learning.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.dtos.responses.PaymentResponse;
import com.learning.api.services.ApiPaymentService;
import com.learning.core.permissions.EHousekeeperPermissions;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    @Autowired
    private ApiPaymentService service;

    @GetMapping
    @EHousekeeperPermissions.isHousekeeperOrCustomer
    public List<PaymentResponse> listPayment() {
        return service.listarPayments();
    }

}
