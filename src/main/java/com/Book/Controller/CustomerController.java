package com.Book.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Entity.Customer;
import com.Book.Entity.logInDto;
import com.Book.Exception.CustomerException;
import com.Book.Exception.UserNotFoundException;
import com.Book.Service.CustomerService;

@RestController
public class CustomerController {

	
	 @Autowired
	    private CustomerService service;

	    //Registering customer
	    @PostMapping("/register")
	    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {


	            Customer registeredCustomer =service.registerCustomer(customer);
	            return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);


	    }


	    //get All customers
//	    @GetMapping("/customers")
//	    public ResponseEntity<String> getAllCustomers(@RequestBody Customer customer)
//	    {
//	        RegistrationResponse response = null;
//	        List<Customer> customerOptional = service.getAllCustomers(customer);
//	        String errorMessage = "No Customers Found";
//	        if (customerOptional.isEmpty())
//	        {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	        }
//	        return ResponseEntity.ok(customerOptional.toString());
//	    }


	    // Login Customer
	    @PostMapping("/login")
	    public String  logIn(@Valid @RequestBody logInDto logInDto)
	    {
	     return service.logInCustomer(logInDto);
	    }

	    @PostMapping("/changePassword")

	    public ResponseEntity<Customer> changePassword( @RequestParam String email,
	                                                  @RequestParam String oldPassword,
	                                                    @RequestParam String newPassword) throws CustomerException{
//	        try {
	            Customer updatedCustomer = service.changePasswordCustomer(email, oldPassword, newPassword);
	            return ResponseEntity.ok(updatedCustomer);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//	        }
	    }


	    @PutMapping("/customer/{name}")
	    public Customer updateCustomer(@Valid @PathVariable("name") String name,  @RequestBody Customer customerData
	                                    ) throws CustomerException, UserNotFoundException
	           {

	        return service.updateCustomer(name, customerData);
	    }

	    @GetMapping("/getcustomers")
	    public List<Customer> getAllCustomers()
	    {
	        List<Customer> customerList = service.getAllCustomers();

	        return customerList;
	    }
}
