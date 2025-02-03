package com.aitech.expense.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

	@NotBlank(message="Please enter name.")
	private String name;
	
	@NotNull(message = "Please enter email.")
	@Email(message = "Please enter valid email.")
	private String email;
	
	@NotNull(message="Please enter password")
	@Size(min=5, message = "Password should be atleast 5 characters.")
	private String password;
	
	private long age=0l;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
}
