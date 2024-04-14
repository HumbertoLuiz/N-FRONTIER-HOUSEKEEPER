package com.learning.web.services;

import java.util.List;

import com.learning.core.models.User;
import com.learning.web.dtos.ChangePasswordForm;
import com.learning.web.dtos.UserInsertForm;
import com.learning.web.dtos.UserUpdateForm;

public interface WebUserService {

	List<User> findAll();

	User insert(UserInsertForm form);

	User findById(Long id);

	User update(UserUpdateForm updateForm, Long id);

	void deleteById(Long id);

	void changePassword(ChangePasswordForm changePasswordForm, String email);
}
