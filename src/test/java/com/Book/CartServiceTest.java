package com.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Book;
import com.Book.Entity.Cart;
import com.Book.Entity.Customer;
import com.Book.Exception.BookException;
import com.Book.Exception.CartException;
import com.Book.Exception.CustomerException;
import com.Book.Repository.BookRepository;
import com.Book.Repository.CartRepository;
import com.Book.Repository.CustomerRepository;
import com.Book.Service.BookService;
import com.Book.Service.CartServiceImpl;
import com.Book.Service.CustomerService;

public class  CartServiceTest{
	
	
	 @Mock
	    private CustomerService customerService;

	    @Mock
	    private BookService bookService;

	    @Mock
	    private CustomerRepository customerRepository;

	    @Mock
	    private CartRepository cartRepository;

	    @Mock 
	    private BookRepository bookRepository;
	    @InjectMocks
	    private CartServiceImpl cartService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testAddBookToCart_Success() throws CartException, CustomerException, BookException {
	        // Create a customer
	        Customer customer = new Customer();
	        customer.setId(1L);
	        Cart cart = new Cart();
	        customer.setCart(cart);

	        // Create a book
	        Book book = new Book();
	        book.setName("Book 1");
	        book.setPrice(10.0);

	        // Mocking necessary methods
	        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));
	        when(bookService.getBookByName("Book 1")).thenReturn(book);
	        when(customerRepository.save(customer)).thenReturn(customer);
	        when(cartRepository.save(cart)).thenReturn(cart);

	        // Call the method
	        Cart resultCart = cartService.addBookToCart("Book 1", 1L);

	        // Assertions
	        assertNotNull(resultCart);
	        assertEquals(1, resultCart.getBookList().size());
	        assertEquals(10.0, resultCart.getTotalCost(), 0.01); // Adding 1 book with price 10.0
	    }

	    @Test
	    void testAddBookToCart_CustomerNotFound() throws CustomerException {
	        // Mocking necessary methods
	        when(customerService.getCustomerById(1L)).thenReturn(Optional.empty());

	        // Assertions
	        assertThrows(CustomerException.class, () -> cartService.addBookToCart("Book 1", 1L));
	    }

	    @Test
	    void testRemoveBookFromCart_Success() throws CartException {
	        // Create a customer's cart
	        Cart cart = new Cart();
	        Book bookToRemove = new Book();
	        bookToRemove.setName("BookToRemove");
	        bookToRemove.setPrice(15.0);
	        cart.getBookList().add(bookToRemove);
	        cart.setTotalCost(15.0);

	        // Mocking necessary methods
	        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
	        when(bookRepository.findBookByName("BookToRemove")).thenReturn(bookToRemove);
	        when(cartRepository.save(cart)).thenReturn(cart);

	        // Call the method
	        Cart resultCart = cartService.removeBookFromCart("BookToRemove", 1L);

	        // Assertions
	        assertNotNull(resultCart);
	        assertEquals(0, resultCart.getBookList().size());
	        assertEquals(0.0, resultCart.getTotalCost(), 0.01);
	    }

	    @Test
	    void testRemoveBookFromCart_CartNotFound() {
	        // Mocking necessary methods
	        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());

	        // Assertions
	        assertThrows(CartException.class, () -> cartService.removeBookFromCart("BookToRemove", 1L));
	    }

	    @Test
	    void testRemoveBookFromCart_BookNotFound() {
	        // Create a customer's cart
	        Cart cart = new Cart();

	        // Mocking necessary methods
	        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
	        when(bookRepository.findBookByName("NonExistentBook")).thenReturn(null);

	        // Assertions
	        assertThrows(CartException.class, () -> cartService.removeBookFromCart("NonExistentBook", 1L));
	    }

	    @Test
	    void testRemoveBookFromCart_BookNotInCart() {
	        // Create a customer's cart
	        Cart cart = new Cart();
	        Book bookToRemove = new Book();
	        bookToRemove.setName("BookToRemove");
	        bookToRemove.setPrice(15.0);

	        // Mocking necessary methods
	        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
	        when(bookRepository.findBookByName("BookToRemove")).thenReturn(bookToRemove);

	        // Assertions
	        assertThrows(CartException.class, () -> cartService.removeBookFromCart("BookToRemove", 1L));
	    }
	  
}