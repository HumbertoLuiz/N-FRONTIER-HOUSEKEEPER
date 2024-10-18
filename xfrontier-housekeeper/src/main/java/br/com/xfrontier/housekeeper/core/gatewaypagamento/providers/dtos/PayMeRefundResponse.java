package br.com.xfrontier.housekeeper.core.gatewaypagamento.providers.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class PayMeRefundResponse {

    private String id;

    private String status;

    private Integer amount;

    private Integer refundedAmount;

}
