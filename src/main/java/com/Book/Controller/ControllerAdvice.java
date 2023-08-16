package com.Book.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Book.Exception.AdminException;
import com.Book.Exception.BookException;
import com.Book.Exception.CartException;
import com.Book.Exception.CartNotFoundException;
import com.Book.Exception.CategoryException;
import com.Book.Exception.CustomerException;
import com.Book.Exception.PaymentNotFoundException;
import com.Book.Exception.UserNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(AdminException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleAdminException(AdminException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(BookException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleBookException(BookException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(CartException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCartException(CartException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCartNotFoundException(CartNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(CategoryException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCategoryException(CategoryException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(CustomerException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCustomerException(CustomerException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handlePaymentNotFoundException(PaymentNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleUserNotFoundException(UserNotFoundException e) {
		return e.getMessage();
	}
	
	
	
	

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

	Map<String, String> errors = new HashMap<>();

	ex.getBindingResult().getAllErrors().forEach(error -> {

	String fieldName = ((FieldError) error).getField();

	String errorMessage = error.getDefaultMessage();

	errors.put(fieldName, errorMessage);

	});

	return errors;

	}
}
