package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.requests.RatingRequest;
import com.learning.api.dtos.responses.MessageResponse;
import com.learning.api.mappers.ApiRatingMapper;
import com.learning.core.exceptions.DailyNotFoundException;
import com.learning.core.models.Daily;
import com.learning.core.models.Rating;
import com.learning.core.models.User;
import com.learning.core.publishers.NewRatingPublisher;
import com.learning.core.repository.DailyRepository;
import com.learning.core.repository.RatingRepository;
import com.learning.core.utils.SecurityUtils;
import com.learning.core.validators.RatingValidator;

@Service
public class ApiRatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private ApiRatingMapper mapper;

    @Autowired
    private RatingValidator validator;

    @Autowired
    private NewRatingPublisher newRatingPublisher;

    public MessageResponse rateDaily(RatingRequest request, Long id) {
        var daily = fingDailyById(id);
        var evaluator = securityUtils.getLoggedUser();
        var model = mapper.toModel(request);
        model.setEvaluator(evaluator);
        model.setDaily(daily);
        model.setVisibility(true);
        model.setEvaluated(getEvaluated(model));

        validator.validate(model);

        model = repository.save(model);
        newRatingPublisher.publish(model);

        return new MessageResponse("Avaliação realizada com sucesso!");
    }

    private User getEvaluated(Rating model) {
        if (model.getEvaluator().isCustomer()) {
            return model.getDaily().getHousekeeper();
        }
        return model.getDaily().getCustomer();
    }

    private Daily fingDailyById(Long id) {
        var message = String.format("Diária com id %d não encontrada", id);
        return dailyRepository.findById(id)
            .orElseThrow(() -> new DailyNotFoundException(message));
    }

}
