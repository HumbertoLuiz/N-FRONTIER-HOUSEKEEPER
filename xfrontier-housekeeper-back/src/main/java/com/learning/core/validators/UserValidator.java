package com.learning.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.learning.core.exceptions.UserAlreadyRegisteredException;
import com.learning.core.exceptions.ValidatingException;
import com.learning.core.models.User;
import com.learning.core.repository.UserRepository;

@Component
public class UserValidator {

	@Autowired
	private UserRepository repository;

	public void validate(User user) {
		validateEmail(user);
	}

	private void validateEmail(User user) {
		if (repository.isEmailAlreadyRegistered(user)) {
			var message = "User Registered Already exists with this e-mail";
			var fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false, null, null, message);
			throw new UserAlreadyRegisteredException(message, fieldError);
		}
		validateCpf(user);
	}

	private void validateCpf(User user) {
		if (repository.isCpfAlreadyRegistered(user)) {
			var message = "There is already a user registered with this cpf";
			var fieldError = new FieldError(user.getClass().getName(), "cpf", user.getCpf(), false, null, null, message);
			throw new UserAlreadyRegisteredException(message, fieldError);
		}
		validateKeyPix(user);
	}

	private void validateKeyPix(User user) {
		if (repository.isKeyPixAlreadyRegistered(user)) {
			var message = "There is already a user registered with this key pix";
			var fieldError = new FieldError(user.getClass().getName(), "keyPix", user.getKeyPix(), false, null, null,
					message);
			throw new UserAlreadyRegisteredException(message, fieldError);
		}
		if (user.isHousekeeper() && user.getKeyPix() == null) {
			var message = "User type Housekeeper must be have a pix key";
			var fieldError = new FieldError(user.getClass().getName(), "keyPix", user.getKeyPix(), false, null, null,
					message);
			throw new ValidatingException(message, fieldError);
		}
	}
}
