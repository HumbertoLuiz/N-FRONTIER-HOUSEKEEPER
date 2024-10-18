package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.requests.RatingRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiRatingMapper;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.Rating;
import br.com.xfrontier.housekeeper.core.models.User;
import br.com.xfrontier.housekeeper.core.publishers.NewRatingPublisher;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.repository.RatingRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;
import br.com.xfrontier.housekeeper.core.validators.RatingValidator;

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
