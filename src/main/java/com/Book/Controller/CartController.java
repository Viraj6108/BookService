package com.Book.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Entity.Cart;
import com.Book.Entity.Payment;
import com.Book.Exception.BookException;
import com.Book.Exception.CartException;
import com.Book.Exception.CustomerException;
import com.Book.Exception.PaymentNotFoundException;
import com.Book.Service.CartService;

@RestController
public class CartController {

	
	 @Autowired
	    private CartService cartService;

	    @PostMapping("/addBook")
	    public ResponseEntity<String> addBookToCart(@RequestParam String bookName, @RequestParam long customerId) throws CustomerException, CartException, BookException {
//	        try {
	            Cart updatedCart = cartService.addBookToCart(bookName, customerId);
	            return ResponseEntity.ok("Book added to cart. Updated cart total cost: " + updatedCart.getTotalCost());
//	        } catch (CartException | CustomerException | BookException e) {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
//	        }
	    }

	    @PostMapping("/removeBook")
	    public ResponseEntity<?> removeBookFromCart(@RequestParam String bookName, @RequestParam long customerId) throws CartException {
//	        try {
	            Cart updatedCart = cartService.removeBookFromCart(bookName, customerId);
	            return ResponseEntity.ok(updatedCart);
//	        } catch (CartException e) {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//	        }
	    }

	    @GetMapping("/getCart")
	    public Optional<Cart> getCartbyId(@RequestParam long cartId)
	    {
	        return cartService.getCartById(cartId);
	    }


	    @GetMapping("/carts")
	    public List<Cart> getAllCarts()
	    {
	        return cartService.getAllCarts();
	    }

	    @PostMapping("/checkout")
	    public ResponseEntity<String> processCheckout(
	            @RequestParam("mode") Payment.PaymentMode mode,
	            @RequestParam("cartId") long cartId
	    ) {
	        try {
	            String result = cartService.checkOut(mode,cartId);
	            return ResponseEntity.ok(result);
	        } catch (PaymentNotFoundException | CustomerException | CartException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        } catch (BookException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
