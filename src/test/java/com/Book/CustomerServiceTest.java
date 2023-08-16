package com.Book;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Cart;
import com.Book.Entity.Customer;
import com.Book.Exception.CustomerException;
import com.Book.Repository.CartRepository;
import com.Book.Repository.CustomerRepository;
import com.Book.Service.CustomerServiceImpl;

public class CustomerServiceTest {

	
	 @Mock
	    private CustomerRepository repository;

	    @Mock
	    private CartRepository cartRepository;

	    @InjectMocks
	    private CustomerServiceImpl customerService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testRegisterCustomer_NewCustomer_Success() throws CustomerException {
	        // Create a new customer
	        Customer newCustomer = new Customer();
	        newCustomer.setId(1L);
	        newCustomer.setEmail("new@example.com");

	        // Mocking necessary methods
	        when(repository.findUserByemail("new@example.com")).thenReturn(null);
	        when(repository.save(newCustomer)).thenReturn(newCustomer);
	        when(cartRepository.save(any(Cart.class))).thenReturn(new Cart());

	        // Call the method
	        Customer resultCustomer = customerService.registerCustomer(newCustomer);

	        // Assertions
	        assertNotNull(resultCustomer);
	        assertEquals("customer", resultCustomer.getRole());
	        assertNotNull(resultCustomer.getCart());
	        assertEquals(0, resultCustomer.getCart().getQuantity());
	        assertEquals(0.0, resultCustomer.getCart().getTotalCost(), 0.01);
	    }
	    @Test
	    void testChangePasswordCustomer_CustomerNotRegistered() {
	     
	        when(repository.findUserByemail("nonexistent@example.com")).thenReturn(null);

	        
	        assertThrows(CustomerException.class, () -> customerService.changePasswordCustomer("nonexistent@example.com", "oldPassword", "newPassword"));
	    }
	    
	    @Test
	    void testUpdateCustomer_CustomerUpdated_Success() throws CustomerException {
	      
	        Customer existingCustomer = new Customer();
	        existingCustomer.setUsername("existingUser");
	        existingCustomer.setEmail("existing@example.com");
	        existingCustomer.setAddress("Existing Address");

	       
	        Customer updatedCustomerData = new Customer();
	        updatedCustomerData.setUsername("newUser");
	        updatedCustomerData.setEmail("new@example.com");
	        updatedCustomerData.setAddress("New Address");

	        
	        when(repository.findByUsername("existingUser")).thenReturn(existingCustomer);
	        when(repository.save(existingCustomer)).thenReturn(existingCustomer);

	      
	        Customer resultCustomer = customerService.updateCustomer("existingUser", updatedCustomerData);

	        
	        assertNotNull(resultCustomer);
	        assertEquals("newUser", resultCustomer.getUsername());
	        assertEquals("new@example.com", resultCustomer.getEmail());
	        assertEquals("New Address", resultCustomer.getAddress());
	    }

	    @Test
	    void testUpdateCustomer_CustomerNotFound() {
	       
	        when(repository.findByUsername("nonexistentUser")).thenReturn(null);

	       
	        assertThrows(CustomerException.class, () -> customerService.updateCustomer("nonexistentUser", new Customer()));
	    }

	    @Test
	    void testUpdateCustomer_FailedToUpdateCustomer() {
	       
	        Customer existingCustomer = new Customer();
	        existingCustomer.setUsername("existingUser");

	       
	        when(repository.findByUsername("existingUser")).thenReturn(existingCustomer);
	        when(repository.save(existingCustomer)).thenThrow(new RuntimeException());

	       
	        assertThrows(CustomerException.class, () -> customerService.updateCustomer("existingUser", new Customer()));
	    }
	    
	    

	    @Test
	    void testUpdateCustomer_CustomerUpdated_Success1() throws CustomerException {
	        // Create an existing customer
	        Customer existingCustomer = new Customer();
	        existingCustomer.setUsername("existingUser");
	        existingCustomer.setEmail("existing@example.com");
	        existingCustomer.setAddress("Existing Address");

	        // Create updated customer data
	        Customer updatedCustomerData = new Customer();
	        updatedCustomerData.setUsername("newUser");
	        updatedCustomerData.setEmail("new@example.com");
	        updatedCustomerData.setAddress("New Address");

	        // Mocking necessary methods
	        when(repository.findByUsername("existingUser")).thenReturn(existingCustomer);
	        when(repository.save(existingCustomer)).thenReturn(existingCustomer);

	        // Call the method
	        Customer resultCustomer = customerService.updateCustomer("existingUser", updatedCustomerData);

	        // Assertions
	        assertNotNull(resultCustomer);
	        assertEquals("newUser", resultCustomer.getUsername());
	        assertEquals("new@example.com", resultCustomer.getEmail());
	        assertEquals("New Address", resultCustomer.getAddress());
	    }

	    @Test
	    void testUpdateCustomer_CustomerNotFound1() {
	        // Mocking necessary methods
	        when(repository.findByUsername("nonexistentUser")).thenReturn(null);

	        // Assertions
	        assertThrows(CustomerException.class, () -> customerService.updateCustomer("nonexistentUser", new Customer()));
	    }
	    
	    
	    @Test
	    void testUpdateCustomer_FailedToUpdateCustomer1() {
	        // Create an existing customer
	        Customer existingCustomer = new Customer();
	        existingCustomer.setUsername("existingUser");

	        // Mocking necessary methods
	        when(repository.findByUsername("existingUser")).thenReturn(existingCustomer);
	        when(repository.save(existingCustomer)).thenThrow(new RuntimeException());

	        // Assertions
	        assertThrows(CustomerException.class, () -> customerService.updateCustomer("existingUser", new Customer()));
	    }
	    
}
