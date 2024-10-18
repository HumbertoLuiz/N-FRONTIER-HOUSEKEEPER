package br.com.xfrontier.housekeeper.api.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.requests.DailyRequest;
import br.com.xfrontier.housekeeper.api.dtos.responses.DailyResponse;
import br.com.xfrontier.housekeeper.api.mappers.ApiDailyMapper;
import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.utils.SecurityUtils;
import br.com.xfrontier.housekeeper.core.validators.DailyValidator;

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
