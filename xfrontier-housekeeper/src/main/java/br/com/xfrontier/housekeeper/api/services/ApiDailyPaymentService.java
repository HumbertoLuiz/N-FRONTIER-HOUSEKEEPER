package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.requests.PaymentRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.gatewaypagamento.adpaters.GatewayPaymentService;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.validators.PaymentValidator;

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
