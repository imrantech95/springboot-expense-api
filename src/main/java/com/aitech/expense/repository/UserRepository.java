package com.aitech.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aitech.expense.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	//User findByEmail(String email);

	 Optional<User> findByEmail(String email);

	       //List<User> findAll(User user);
}
