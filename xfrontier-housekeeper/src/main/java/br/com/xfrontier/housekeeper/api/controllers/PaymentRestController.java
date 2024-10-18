package br.com.xfrontier.housekeeper.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xfrontier.housekeeper.api.dtos.responses.PaymentResponse;
import br.com.xfrontier.housekeeper.api.services.ApiPaymentService;
import br.com.xfrontier.housekeeper.core.permissions.EHousekeeperPermissions;

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
