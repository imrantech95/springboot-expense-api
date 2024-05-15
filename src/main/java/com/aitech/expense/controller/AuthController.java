package com.aitech.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aitech.expense.entity.AuthModel;
import com.aitech.expense.entity.User;
import com.aitech.expense.entity.UserModel;
import com.aitech.expense.service.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authModel) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		// return new ResponseEntity<String>("user is logged in ", HttpStatus.OK);
	}
}
