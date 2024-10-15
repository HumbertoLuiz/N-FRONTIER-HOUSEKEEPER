package com.learning.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.responses.PaymentResponse;
import com.learning.api.mappers.ApiPaymentMapper;
import com.learning.core.enums.DailyStatus;
import com.learning.core.repository.PaymentRepository;
import com.learning.core.utils.SecurityUtils;

@Service
public class ApiPaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ApiPaymentMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<PaymentResponse> listarPayments() {
        var userLogged = securityUtils.getLoggedUser();
        var status = List.of(
            DailyStatus.CONCLUDED,
            DailyStatus.EVALUATED,
            DailyStatus.TRANSFERRED
        );

        return repository.findByDailyHousekeeperAndDailyStatusIn(userLogged, status)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
