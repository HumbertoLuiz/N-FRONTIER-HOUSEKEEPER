package br.com.xfrontier.housekeeper.web.services;

import java.util.List;

import br.com.xfrontier.housekeeper.core.models.User;
import br.com.xfrontier.housekeeper.web.dtos.ChangePasswordForm;
import br.com.xfrontier.housekeeper.web.dtos.UserInsertForm;
import br.com.xfrontier.housekeeper.web.dtos.UserUpdateForm;

public interface WebUserService {

	List<User> findAll();

	User insert(UserInsertForm form);

	User findById(Long id);

	User update(UserUpdateForm updateForm, Long id);

	void deleteById(Long id);

	void changePassword(ChangePasswordForm changePasswordForm, String email);
}
