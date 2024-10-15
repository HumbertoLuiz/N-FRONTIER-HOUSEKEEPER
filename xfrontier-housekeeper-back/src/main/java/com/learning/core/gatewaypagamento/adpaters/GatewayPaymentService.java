package com.learning.core.gatewaypagamento.adpaters;

import com.learning.core.models.Daily;
import com.learning.core.models.Payment;

public interface GatewayPaymentService {

    Payment pay(Daily daily, String cardHash);

    Payment makeTotalRefund(Daily daily);

    Payment makePartialRefund(Daily daily);

}
