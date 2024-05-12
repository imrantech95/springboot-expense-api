package com.aitech.expense.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aitech.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Page<Expense> findByNameContaining(String keyword, Pageable page);
	List<Expense> findByCategory(String keyword, Pageable page);
	
	Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
}
