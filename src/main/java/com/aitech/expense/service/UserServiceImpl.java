package com.aitech.expense.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aitech.expense.entity.User;
import com.aitech.expense.entity.UserModel;
import com.aitech.expense.exceptions.ItemAlreadyExistsException;
import com.aitech.expense.exceptions.ResourceNotFoundException;
import com.aitech.expense.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User createUser(UserModel uModel) {
		if (userRepo.existsByEmail(uModel.getEmail()))
			throw new ItemAlreadyExistsException("User is already register with this email: " + uModel.getEmail());

		User user = new User();
		BeanUtils.copyProperties(uModel, user);
		return userRepo.save(user);
	}

	@Override
	public User readUser(long id) throws ResourceNotFoundException {
		return userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found for the id: " + id));

	}

	@Override
	public User updateUser(User user, long id) {
		User existingUser = readUser(id);

		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
		// existingUser.setAge(user.getAge() != null ? user.getAge() :
		// existingUser.getAge());

		return userRepo.save(existingUser);
	}

	@Override
	public Page<User> findAllUsers(Pageable page) {
		return userRepo.findAll(page);
	}

	@Override
	public void deleteUser(long id) {
		   User user=readUser(id);
			userRepo.delete(user);
		
	}

}
