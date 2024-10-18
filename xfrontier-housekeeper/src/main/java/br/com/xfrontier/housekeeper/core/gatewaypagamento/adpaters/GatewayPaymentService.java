package br.com.xfrontier.housekeeper.core.gatewaypagamento.adpaters;

import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.Payment;

public interface GatewayPaymentService {

    Payment pay(Daily daily, String cardHash);

    Payment makeTotalRefund(Daily daily);

    Payment makePartialRefund(Daily daily);

}
