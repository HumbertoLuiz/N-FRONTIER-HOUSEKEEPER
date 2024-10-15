package com.learning.api.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.learning.api.dtos.responses.PaymentResponse;
import com.learning.core.enums.DailyStatus;
import com.learning.core.models.Payment;

@Mapper(componentModel = "spring")
public interface ApiPaymentMapper {

	ApiPaymentMapper INSTANCE = Mappers.getMapper(ApiPaymentMapper.class);

    @Mapping(target = "status", source = "daily.status")
    @Mapping(target = "value", source = "daily.price")
    @Mapping(target = "depositValue", source = "model")
    PaymentResponse toResponse(Payment model);

    default BigDecimal mapDepositValue(Payment model) {
        var priceDaily = model.getDaily().getPrice();
        var comission = model.getDaily().getValueCommission();

        return priceDaily.subtract(comission);
    }

    default Integer dailyStatusToInteger(DailyStatus status) {
        return status.equals(DailyStatus.TRANSFERRED) ? 1 : 2;
    }

}
