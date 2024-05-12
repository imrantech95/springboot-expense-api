package com.aitech.expense.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aitech.expense.entity.Expense;
import com.aitech.expense.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
		// int number=1;
		// calculateFactorial(number);
		return expenseService.getAllExpenses(page).toList();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable long id) {
		return expenseService.getExpenseById(id);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam long id) {
		expenseService.deleteExpenseById(id);
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expense")
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpenseDetails(expense);
	}

	@PutMapping("/expense/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable long id) {
		return expenseService.updateExpenseDetails(id, expense);
	}

	public int calculateFactorial(int number) {
		return number * calculateFactorial(number - 1);
	}

	@GetMapping("/expenses/name")
	public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable page) {
		return expenseService.readByName(name, page);
	}

	@GetMapping("/expenses/category")
	public List<Expense> getAllExpensesByCategory(@RequestParam String category, Pageable page) {
		return expenseService.readByCategory(category, page);
	}

	@GetMapping("/expenses/date")
	public List<Expense> getAllExpenseByDate(
			@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, 
			Pageable page) {
		return expenseService.readByDate(startDate, endDate, page);

	}
}
