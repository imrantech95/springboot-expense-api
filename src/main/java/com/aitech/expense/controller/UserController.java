package com.aitech.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aitech.expense.entity.User;
import com.aitech.expense.entity.UserModel;
import com.aitech.expense.exceptions.ResourceNotFoundException;
import com.aitech.expense.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login() {
		return new ResponseEntity<String>("user is logged in ", HttpStatus.OK);
	}

	@GetMapping("/users")
	public List<User> getAllUsers(Pageable page) {
		return userService.findAllUsers(page).toList();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateExpenseDetails(@RequestBody User user, @PathVariable long id) {
		User update = userService.updateUser(user, id);
		return new ResponseEntity<User>(update, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUsers(@PathVariable long id) throws ResourceNotFoundException {
		userService.deleteUser(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}
}
