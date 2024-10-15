package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.requests.PaymentRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.core.enums.DailyStatus;
import com.learning.core.exceptions.DailyNotFoundException;
import com.learning.core.gatewaypagamento.adpaters.GatewayPaymentService;
import com.learning.core.models.Daily;
import com.learning.core.repository.DailyRepository;
import com.learning.core.validators.PaymentValidator;

@Service
public class ApiDailyPaymentService {

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private PaymentValidator validator;

    @Autowired
    private GatewayPaymentService gatewayPaymentService;

    public MessageResponse pay(PaymentRequest request, Long id) {
        var daily = findDailyById(id);

        validator.validate(daily);

        var payment = gatewayPaymentService.pay(daily, request.getCardHash());
        if (payment.isAceito()) {
            daily.setStatus(DailyStatus.PAID);
            dailyRepository.save(daily);
            return new MessageResponse("Daily rate successfully paid!");
        }
        return new MessageResponse("Payment refused");
    }

    private Daily findDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return dailyRepository.findById(id)
            .orElseThrow(() -> new DailyNotFoundException(message));
    }

}
