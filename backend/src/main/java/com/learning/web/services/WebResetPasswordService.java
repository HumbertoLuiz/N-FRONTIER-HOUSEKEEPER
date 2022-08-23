package com.learning.web.services;

import javax.validation.Valid;

import com.learning.web.dtos.ResetConfirmPasswordForm;

public interface WebResetPasswordService {

	void confirmResetPassword(String token, @Valid ResetConfirmPasswordForm form);

}
