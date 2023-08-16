package com.Book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Customer;
import com.Book.Entity.logInDto;
import com.Book.Entity.Cart;
import com.Book.Exception.CustomerException;
import com.Book.Repository.CartRepository;
import com.Book.Repository.CustomerRepository;
import com.Book.Repository.UsersRepository;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private UsersRepository userReopository;

	@Autowired
	private CartRepository cartRepository;
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		Customer customerFromDB = repository.findUserByemail(customer.getEmail());


		if(customerFromDB != null && customerFromDB.getEmail().equals(customer.getEmail()))
		{

			 throw new CustomerException("User already registered");
		}
		Customer newCustomer = repository.save(customer);
		Cart cart = new Cart(customer.getId(), 0, 0.0);
		cartRepository.save(cart);
		customer.setCart(cart);
		customer.setRole("customer");
		repository.save(customer);
		return newCustomer;

	}

	@Override
	public Customer changePasswordCustomer(String email, String oldPassword, String newPassword) throws  CustomerException {
		// check if the user is present or not
		Customer customerFromDB = repository.findUserByemail(email);


		if(customerFromDB!= null)
		{
			if(customerFromDB.getPassword().equals(oldPassword))
			{
				  customerFromDB.setPassword(newPassword);
				  return repository.save(customerFromDB);
			}
			else{
				 throw new CustomerException("Password does not match");
			}
		}else {
			throw new CustomerException("Customer is not registered");
		}
	}

	

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = repository.findAll();
		return customerList;
	}

	@Override
	public Optional<Customer> getCustomerById(long custId) throws CustomerException {
		Optional<Customer> customer = repository.findById(custId);
		if (customer == null)
		{
			throw new CustomerException("Customer not found with this id");
		}
		return customer;
	}


	@Override
	public Customer updateCustomer(String username,Customer customerData) throws CustomerException {
		Customer existingCustomer = repository.findByUsername(username);
		if (existingCustomer == null) {
			throw new CustomerException("Customer not found with the given username");
		}
		existingCustomer.setUsername(customerData.getUsername());
		existingCustomer.setEmail(customerData.getEmail());
		existingCustomer.setAddress(customerData.getAddress());
		try {
			return repository.save(existingCustomer);
		} catch (Exception e) {
			throw new CustomerException("Failed to update customer. Try again after sometime");
		}
	}

	@Override
	public String logInCustomer(logInDto logInDto) {
		Customer  customerFromDB = repository.findUserByemail(logInDto.getEmail());

		if(customerFromDB != null)
		{
			if (customerFromDB.getEmail().equals(logInDto.getEmail()) && customerFromDB.getPassword().equals(logInDto.getPassword())) {
				return "User logged in successfully";
			} else {
				return "Incorrect email or password";
			}
		}
		return "User does not exist";
	}
	
	
}
