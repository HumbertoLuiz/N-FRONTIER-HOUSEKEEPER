package br.com.xfrontier.housekeeper.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xfrontier.housekeeper.api.dtos.responses.MessageResponse;
import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.validators.ConfirmationPresenceValidator;

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
