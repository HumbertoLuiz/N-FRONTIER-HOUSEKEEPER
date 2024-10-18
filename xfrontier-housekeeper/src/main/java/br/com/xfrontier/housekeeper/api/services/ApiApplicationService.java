package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;
import br.com.xfrontier.housekeeper.core.validators.ApplicationValidator;

@Service
public class ApiApplicationService {

    @Autowired
    private DailyRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ApplicationValidator validator;

    public MessageResponse apply(Long id) {
        var daily = findDailyById(id);
        validator.validate(daily);
        var loggedUser = securityUtils.getLoggedUser();
        daily.getApplicants().add(loggedUser);
        repository.save(daily);
        return new MessageResponse("Successful application!");
    }

    private Daily findDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return repository.findById(id)
            .orElseThrow(() -> new DailyNotFoundException(message));
    }

}
