package com.aitech.expense.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aitech.expense.entity.User;
import com.aitech.expense.entity.UserModel;

public interface UserService {

	User createUser(UserModel user);
	User readUser(long id);
	User updateUser( User user,long id);
	Page<User> findAllUsers(Pageable page);
	void deleteUser(long id);
	
}
