package com.learning.core.gatewaypagamento.providers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayMeTransactionResponse {

    private String id;

    private String status;

    private Integer amount;

}
