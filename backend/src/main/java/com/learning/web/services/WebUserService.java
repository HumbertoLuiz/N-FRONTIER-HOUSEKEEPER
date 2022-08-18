package com.learning.web.services;

import java.util.List;

import com.learning.core.models.User;
import com.learning.web.dtos.UserInsertForm;

public interface WebUserService {

	List<User> findAll();

	User insert(UserInsertForm form);

//	User findById(Long id);
//
//	User update(UserForm form, Long id);
//
//	void deleteById(Long id);
}
