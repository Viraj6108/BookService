package com.Book.Service;

import java.util.List;
import java.util.Optional;

import com.Book.Entity.Customer;
import com.Book.Entity.logInDto;
import com.Book.Exception.CustomerException;



public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws CustomerException;
	public Customer changePasswordCustomer (String newPassword, String oldPassword, String email) throws  CustomerException;
	public String logInCustomer (logInDto logInDto);

	public List<Customer> getAllCustomers();
	
    public Optional<Customer> getCustomerById(long custId) throws CustomerException;

	public Customer updateCustomer (String username,Customer customerData) throws CustomerException;
}
