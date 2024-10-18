package br.com.xfrontier.housekeeper.web.services;

import jakarta.validation.Valid;

import br.com.xfrontier.housekeeper.web.dtos.ResetConfirmPasswordForm;

public interface WebResetPasswordService {

	void confirmResetPassword(String token, @Valid ResetConfirmPasswordForm form);

}
