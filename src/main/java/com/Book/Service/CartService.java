package com.Book.Service;

import java.util.List;
import java.util.Optional;

import com.Book.Entity.Cart;
import com.Book.Entity.Payment;
import com.Book.Exception.BookException;
import com.Book.Exception.CartException;
import com.Book.Exception.CustomerException;
import com.Book.Exception.PaymentNotFoundException;


public interface CartService {

	
	  public Cart addBookToCart(String bookName,long customerid) throws CartException, CustomerException, BookException;

	    public Cart removeBookFromCart(String  bookName, long customerId) throws CartException;

	    public Optional<Cart> getCartById(long cartId);


	    List<Cart> getAllCarts();

	    //Check out function


	    //Check out function
	    String checkOut(Payment.PaymentMode mode, long cartId) throws PaymentNotFoundException, CustomerException, CartException, BookException;
}
