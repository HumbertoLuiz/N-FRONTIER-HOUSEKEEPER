package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.responses.MessageResponse;
import com.learning.core.enums.DailyStatus;
import com.learning.core.exceptions.DailyNotFoundException;
import com.learning.core.models.Daily;
import com.learning.core.repository.DailyRepository;
import com.learning.core.validators.ConfirmationPresenceValidator;

@Service
public class ApiConfirmationPresenceService {

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private ConfirmationPresenceValidator validator;

    public MessageResponse confirmPresence(Long id) {
        var daily = findDailyById(id);
        validator.validate(daily);
        daily.setStatus(DailyStatus.CONCLUDED);
        dailyRepository.save(daily);
        return new MessageResponse("Attendance successfully confirmed!");
    }

    private Daily findDailyById(Long id) {
        var mensagem = String.format("Daily id %d not found", id);
        return dailyRepository.findById(id)
            .orElseThrow(() -> new DailyNotFoundException(mensagem));
    }

}
