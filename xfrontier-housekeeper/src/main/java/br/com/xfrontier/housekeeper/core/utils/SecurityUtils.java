package br.com.xfrontier.housekeeper.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.xfrontier.housekeeper.core.enums.UserType;
import br.com.xfrontier.housekeeper.core.exceptions.DailyNotFoundException;
import br.com.xfrontier.housekeeper.core.exceptions.UserNotFoundException;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.User;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.repository.UserRepository;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DailyRepository dailyRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Boolean isCustomer() {
        var authentication = getAuthentication();
        var customerType = UserType.CUSTOMER.name();
        return authentication.getAuthorities()
            .stream()
            .anyMatch(authority -> authority.getAuthority().equals(customerType));
    }

    public String getEmailLoggedUser() {
        return getAuthentication().getName();
    }

    public User getLoggedUser() {
        var email = getEmailLoggedUser();
        var message = String.format("User with email %s not found", email);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(message));
    }

    public Boolean isClientDaily(Long id) {
        var daily = findDailyById(id);
        var loggedUser = getLoggedUser();

        if (!loggedUser.isCustomer()) {
            return false;
        }

        return daily.getCustomer().equals(loggedUser);
    }

    public Boolean isClientOrHousekeeperDaily(Long id) {
        var daily = findDailyById(id);
        var loggedUser = getLoggedUser();

        var isClientDaily = daily.getCustomer() != null && daily.getCustomer().equals(loggedUser);
        var isHousekeeperDaily = daily.getHousekeeper() != null && daily.getHousekeeper().equals(loggedUser);

        return isClientDaily || isHousekeeperDaily;
    }

    private Daily findDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return dailyRepository.findById(id)
            .orElseThrow(() -> new DailyNotFoundException(message));
    }

}
