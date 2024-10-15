package com.learning.core.gatewaypagamento.providers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.core.enums.PaymentStatus;
import com.learning.core.exceptions.PaymentNotFoundException;
import com.learning.core.gatewaypagamento.adpaters.GatewayPaymentService;
import com.learning.core.gatewaypagamento.exceptions.GatewayPaymentException;
import com.learning.core.gatewaypagamento.providers.dtos.PayMeRefundRequest;
import com.learning.core.gatewaypagamento.providers.dtos.PayMeRefundResponse;
import com.learning.core.gatewaypagamento.providers.dtos.PayMeTransactionRequest;
import com.learning.core.gatewaypagamento.providers.dtos.PayMeTransactionResponse;
import com.learning.core.models.Daily;
import com.learning.core.models.Payment;
import com.learning.core.repository.PaymentRepository;

@Service
public class PayMeService implements GatewayPaymentService {

    private static final String BASE_URL = "https://api.pay.me/1";

    private final RestTemplate clienteHttp = new RestTemplate();

    @Value("${com.learning.payme.apiKey}")
    private String apiKey;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Payment pay(Daily daily, String cardHash) {
        try {
            return tryPay(daily, cardHash);
        } catch (HttpClientErrorException.BadRequest exception) {
            var responseBody = exception.getResponseBodyAsString();
            var jsonNode = getJsonNode(responseBody);
            var message = jsonNode.path("errors")
                .path(0)
                .path("message")
                .asText();
            throw new GatewayPaymentException(message);
        }
    }

    @Override
    public Payment makeTotalRefund(Daily daily) {
        try {
            return tryMakeTotalRefund(daily);
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new GatewayPaymentException(exception.getLocalizedMessage());
        }
    }

    @Override
    public Payment makePartialRefund(Daily daily) {
        try {
            return tryMakePartialRefund(daily);
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new GatewayPaymentException(exception.getLocalizedMessage());
        }
    }

    private Payment tryMakePartialRefund(Daily daily) {
        var request = PayMeRefundRequest.builder()
            .amount(convertRealsToCents(daily.getPrice().divide(new BigDecimal(2))))
            .apiKey(apiKey)
            .build();
        return makeRefund(daily, request);
    }

    private Payment tryMakeTotalRefund(Daily daily) {
        var request = PayMeRefundRequest.builder()
            .apiKey(apiKey)
            .build();
        return makeRefund(daily, request);
    }

    private Payment makeRefund(Daily daily, PayMeRefundRequest request) {
    	validateDailyForRefund(daily);
        var payment = getPaymentDaDaily(daily);
        var url = BASE_URL + "/transactions/{transaction_id}/refund";
        var response = clienteHttp.postForEntity(url, request, PayMeRefundResponse.class, payment.getTransactionId());
        return createPayment(daily, response.getBody());
    }

    private void validateDailyForRefund(Daily daily) {
        var isNotPaidOrNotConfirmed = !(daily.isPaid() || daily.isConfirmed());
        if (isNotPaidOrNotConfirmed) {
            throw new IllegalArgumentException("No refunds can be made for unpaid per diems");
        }
    }

    private Payment getPaymentDaDaily(Daily daily) {
        return daily.getPayments()
            .stream()
            .filter(this::isPaymentAceito)
            .findFirst()
            .orElseThrow(PaymentNotFoundException::new);
    }

    private boolean isPaymentAceito(Payment payment) {
        return payment.getStatus().equals(PaymentStatus.ACCEPTED);
    }

    private Payment tryPay(Daily daily, String cardHash) {
        var transactionRequest = createTransactionRequest(daily, cardHash);
        var url = BASE_URL + "/transactions";
        var response = clienteHttp.postForEntity(url, transactionRequest, PayMeTransactionResponse.class);
        return createPayment(daily, response.getBody());
    }

    private Payment createPayment(Daily daily, PayMeTransactionResponse body) {
        var payment = Payment.builder()
            .value(daily.getPrice())
            .transactionId(body.getId())
            .daily(daily)
            .status(createPaymentStatus(body.getStatus()))
            .build();
        return paymentRepository.save(payment);
    }

    private Payment createPayment(Daily daily, PayMeRefundResponse body) {
        var payment = Payment.builder()
            .value(convertCentsToReals(body.getRefundedAmount()))
            .transactionId(body.getId())
            .daily(daily)
            .status(PaymentStatus.REFUNDED)
            .build();
        return paymentRepository.save(payment);
    }

    private PaymentStatus createPaymentStatus(String transactionStatus) {
        return transactionStatus.equals("paid") ? PaymentStatus.ACCEPTED : PaymentStatus.REPROVED;
    }

    private PayMeTransactionRequest createTransactionRequest(Daily daily, String cardHash) {
        return PayMeTransactionRequest.builder()
            .amount(convertRealsToCents(daily.getPrice()))
            .cardHash(cardHash)
            .async(false)
            .apiKey(apiKey)
            .build();
    }

    private Integer convertRealsToCents(BigDecimal price) {
        return price.multiply(new BigDecimal(100)).intValue();
    }

    private BigDecimal convertCentsToReals(Integer price) {
        return new BigDecimal(price).divide(new BigDecimal(100));
    }

    private JsonNode getJsonNode(String responseBody) {
        try {
            return objectMapper.readTree(responseBody);
        } catch (JsonProcessingException exception) {
            throw new GatewayPaymentException(exception.getLocalizedMessage());
        }
    }

}
