package com.learning.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.learning.core.enums.UserType;
import com.learning.core.exceptions.IncorrectPasswordException;
import com.learning.core.exceptions.PasswordDoesntMatchException;
import com.learning.core.exceptions.UserNotFoundException;
import com.learning.core.models.User;
import com.learning.core.repository.UserRepository;
import com.learning.core.validator.UserValidator;
import com.learning.web.dtos.ChangePasswordForm;
import com.learning.web.dtos.UserInsertForm;
import com.learning.web.dtos.UserUpdateForm;
import com.learning.web.interfaces.IConfirmPassword;
import com.learning.web.mappers.WebUserMapper;
import com.learning.web.services.WebUserService;

@Service
public class WebUserServiceImp implements WebUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebUserMapper webUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator validator;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User insert(UserInsertForm form) {
    	validateConfirmPassword((IConfirmPassword) form);

        var model = webUserMapper.toModel(form);

        var passwordHash = passwordEncoder.encode(model.getPassword());

        model.setPassword(passwordHash);
        model.setUserType(UserType.ADMIN);

        validator.validate(model);

        return userRepository.save(model);
    }

    public User findById(Long id) {
        var message = String.format("User with ID %d not found", id);

        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(message));
    }

    public User findByEmail(String email) {
        var message = String.format("User with email %s not found", email);

        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(message));
    }

    public UserUpdateForm findFormById(Long id) {
        var user = findById(id);

        return webUserMapper.toForm(user);
    }

    public User update(UserUpdateForm form, Long id) {
        var user = findById(id);

        var model = webUserMapper.toModel(form);
        model.setId(user.getId());
        model.setPassword(user.getPassword());
        model.setUserType(user.getUserType());

        validator.validate(model);

        return userRepository.save(model);
    }

    public void deleteById(Long id) {
        var user = findById(id);

        userRepository.delete(user);
    }

    public void changePassword(ChangePasswordForm form, String email) {
        var user = findByEmail(email);

        validateConfirmPassword(form);

        var currentPassword = user.getPassword();
        var oldPassword = form.getOldPassword();
        var password = form.getPassword();

        if (!passwordEncoder.matches(oldPassword, currentPassword)) {
            var message = "The old password is incorrect";
            var fieldError = new FieldError(form.getClass().getName(), "oldPassword", oldPassword, false, null, null, message);

            throw new IncorrectPasswordException(message, fieldError);
        }

        var newPasswordHash = passwordEncoder.encode(password);
        user.setPassword(newPasswordHash);
        userRepository.save(user);
    }

    private void validateConfirmPassword(IConfirmPassword obj) {
        var password = obj.getPassword();
        var confirmPassword = obj.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            var message = "fields doesn't match";
            var fieldError = new FieldError(obj.getClass().getName(), "confirmPassword", obj.getConfirmPassword(), false, null, null, message);

            throw new PasswordDoesntMatchException(message, fieldError);
        }
    }
}