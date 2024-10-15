package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.responses.MessageResponse;
import com.learning.core.exceptions.DailyNotFoundException;
import com.learning.core.models.Daily;
import com.learning.core.repository.DailyRepository;
import com.learning.core.utils.SecurityUtils;
import com.learning.core.validators.ApplicationValidator;

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
