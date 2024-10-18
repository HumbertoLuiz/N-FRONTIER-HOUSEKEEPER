package br.com.xfrontier.housekeeper.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.responses.PaymentResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiPaymentMapper;
import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.repository.PaymentRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;

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
