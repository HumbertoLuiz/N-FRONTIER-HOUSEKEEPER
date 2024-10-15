package com.learning.api.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.requests.DailyRequest;
import com.learning.api.dtos.responses.DailyResponse;
import com.learning.api.mappers.ApiDailyMapper;
import com.learning.core.enums.DailyStatus;
import com.learning.core.exceptions.DailyNotFoundException;
import com.learning.core.models.Daily;
import com.learning.core.repository.DailyRepository;
import com.learning.core.utils.SecurityUtils;
import com.learning.core.validators.DailyValidator;

@Service
public class ApiDailyService {

    @Autowired
    private DailyRepository repository;

    @Autowired
    private ApiDailyMapper mapper;

    @Autowired
    private DailyValidator validator;

    @Autowired
    private SecurityUtils securityUtils;

    public DailyResponse register(DailyRequest request) {
        var model = mapper.toModel(request);

        model.setValueCommission(calculateComission(model));
        model.setCustomer(securityUtils.getLoggedUser());
        model.setStatus(DailyStatus.NO_PAYMENT);

        validator.validate(model);

        var modelRegister = repository.save(model);

        return mapper.toResponse(modelRegister);
    }

    public List<DailyResponse> listByLoggedUser() {
        var userLogged = securityUtils.getLoggedUser();

        List<Daily> daily;

        if (userLogged.isCustomer()) {
        	daily = repository.findByCustomer(userLogged);
        } else {
        	daily = repository.findByHousekeeper(userLogged);
        }

        return daily.stream()
            .map(mapper::toResponse)
            .toList();
    }

    public DailyResponse findById(Long id) {
        var daily = findDailyById(id);

        return mapper.toResponse(daily);
    }

    private Daily findDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return repository.findById(id)
            .orElseThrow(() -> new DailyNotFoundException(message));
    }

    private BigDecimal calculateComission(Daily model) {
        var job = model.getJob();
        var price = model.getPrice();
        var percentComission = job.getPercentComission();
        var bigDecimal100 = new BigDecimal(100);

        return price.multiply(percentComission.divide(bigDecimal100)).setScale(2);
    }

}
