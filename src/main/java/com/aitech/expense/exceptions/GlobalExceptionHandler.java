package com.aitech.expense.exceptions;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aitech.expense.entity.ErrorObject;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFounfException(ResourceNotFoundException ex,
			WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());

		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ErrorObject errorObject = new ErrorObject();

		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());

		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request) {

		ErrorObject errorObject = new ErrorObject();

		errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());

		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<ErrorObject> handleItemExistsException(ItemAlreadyExistsException ex, WebRequest request) {

		ErrorObject errorObject = new ErrorObject();

		errorObject.setStatusCode(HttpStatus.CONFLICT.value());

		errorObject.setMessage(ex.getMessage());

		errorObject.setTimestamp(new Date());

		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("statusCode", HttpStatus.BAD_REQUEST.value());
		body.put("timestamp", new Date());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("message", errors);

		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);

	}

}
