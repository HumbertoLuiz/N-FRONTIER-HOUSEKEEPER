package com.learning.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.learning.core.exceptions.UserNotFoundException;
import com.learning.core.models.User;
import com.learning.core.repository.UserRepository;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getEmailLoggedUser() {
        return getAuthentication().getName();
    }

    public User getLoggedUser() {
        var email= getEmailLoggedUser();
        var message= String.format("User with email %s not found", email);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(message));

    }
}
