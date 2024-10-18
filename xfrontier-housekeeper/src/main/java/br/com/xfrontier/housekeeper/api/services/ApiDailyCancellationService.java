package br.com.xfrontier.housekeeper.api.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.xfrontier.housekeeper.api.dtos.requests.DailyCancellationRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.gatewaypagamento.adpaters.GatewayPaymentService;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.Rating;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.repository.RatingRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;
import br.com.xfrontier.housekeeper.core.validators.DailyCancellationValidator;

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
