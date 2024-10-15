package com.learning.api.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.api.dtos.requests.DailyCancellationRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.core.enums.DailyStatus;
import com.learning.core.exceptions.DailyNotFoundException;
import com.learning.core.gatewaypagamento.adpaters.GatewayPaymentService;
import com.learning.core.models.Daily;
import com.learning.core.models.Rating;
import com.learning.core.repository.DailyRepository;
import com.learning.core.repository.RatingRepository;
import com.learning.core.utils.SecurityUtils;
import com.learning.core.validators.DailyCancellationValidator;

@Service
public class ApiDailyCancellationService {

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private DailyCancellationValidator validator;

    @Autowired
    private GatewayPaymentService gatewayPaymentService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional(readOnly = false)
    public MessageResponse cancel(Long dailyId, DailyCancellationRequest request) {
        var daily = findDailyById(dailyId);
        validator.validar(daily);

        if (hasPenalization(daily)) {
        	applyPenalization(daily);
        } else {
            gatewayPaymentService.makeTotalRefund(daily);
        }

        daily.setStatus(DailyStatus.CANCELLED);
        daily.setReasonCancellation(request.getReasonCancellation());
        dailyRepository.save(daily);

        return new MessageResponse("The per diem was successfully canceled!");
    }

    private void applyPenalization(Daily daily) {
        var loggedUser = securityUtils.getLoggedUser();
        if (loggedUser.isHousekeeper()) {
        	penalizeHousekeeper(daily);
            gatewayPaymentService.makeTotalRefund(daily);
        } else {
            gatewayPaymentService.makePartialRefund(daily);
        }
    }

    private void penalizeHousekeeper(Daily daily) {
        var rating = Rating.builder()
            .score(1.0)
            .description("Daily penalty canceled")
            .evaluated(daily.getHousekeeper())
            .visibility(false)
            .daily(daily)
            .build();
        ratingRepository.save(rating);
    }

    private Daily findDailyById(Long dailyId) {
        var message = String.format("Daily with id %d not found", dailyId);
        return dailyRepository.findById(dailyId)
            .orElseThrow(() -> new DailyNotFoundException(message));
    }

    private boolean hasPenalization(Daily daily) {
        var today = LocalDateTime.now();
        return ChronoUnit.HOURS.between(today, daily.getDateService()) < 24;
    }

}
