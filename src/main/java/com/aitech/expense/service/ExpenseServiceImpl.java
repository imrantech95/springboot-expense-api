package com.aitech.expense.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aitech.expense.entity.Expense;
import com.aitech.expense.exceptions.ResourceNotFoundException;
import com.aitech.expense.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepo;

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return expenseRepo.findAll(page);
	}

	public Expense getExpenseById(long id) {
		Optional<Expense> expense = expenseRepo.findById(id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Expense is not found for the id: " + id);
	}

	@Override
	public void deleteExpenseById(long id) {
		Expense expense = getExpenseById(id);
		expenseRepo.delete(expense);
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		return expenseRepo.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(
				expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense
				.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());

		return expenseRepo.save(existingExpense);
	}

	@Override
	public List<Expense> readByName(String name, Pageable page) {
		return expenseRepo.findByNameContaining(name, page).toList();
	}

	@Override
	public List<Expense> readByCategory(String category, Pageable page) {

		return expenseRepo.findByCategory(category, page);
	}

	@Override
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
		if (startDate == null) {
			startDate = new Date();
		}
		if (endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		Page<Expense> pages = expenseRepo.findByDateBetween(startDate, endDate, page);

		return pages.toList();
	}

}
