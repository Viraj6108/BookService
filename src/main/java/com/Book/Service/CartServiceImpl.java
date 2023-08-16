package com.Book.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Book;
import com.Book.Entity.Cart;
import com.Book.Entity.Customer;
import com.Book.Entity.Payment;
import com.Book.Entity.Orders;
import com.Book.Exception.BookException;
import com.Book.Exception.CartException;
import com.Book.Exception.CustomerException;
import com.Book.Exception.PaymentNotFoundException;
import com.Book.Repository.BookRepository;
import com.Book.Repository.CartRepository;
import com.Book.Repository.CustomerRepository;
import com.Book.Repository.PaymentRepository;



@Service
public class CartServiceImpl implements CartService{

	
	 @Autowired
	    private CartRepository cartRepository;
	    @Autowired
	    private CustomerService customerService;

	    @Autowired
	    private CustomerRepository customerRepository;
	    @Autowired
	    private BookService bookService;

	    @Autowired
	    private PaymentRepository paymentRepository;

	    @Autowired
	    private OrdersService orderService;
	@Autowired
	private BookRepository bookRepository;

	    @Override
	    public Cart addBookToCart(String bookName, long customerId) throws CartException, CustomerException, BookException {
	        // Get the customer by ID
	        Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);

	        if (optionalCustomer.isEmpty()) {
	            throw new CustomerException("Customer not found with this id");
	        }

	        Customer customer = optionalCustomer.get();
	        Cart cart = customer.getCart();
	       // Book book = bookService.getBookById(bookId);
	        Book book = bookService.getBookByName(bookName);
	        cart.getBookList().add(book);
	        cart.setQuantity(cart.getBookList().size());
	        cart.setTotalCost(updateCartTotalCost(cart));
	        customerRepository.save(customer);
	        return cartRepository.save(cart);
	    }



	    // Calculate and update the cart's total cost based on the book list
	    private double updateCartTotalCost(Cart cart) {
	        double totalCost = 0.0;
	        for (Book book : cart.getBookList()) {
	            totalCost += book.getPrice(); // Assuming getPrice() returns the book's price as a double
	        }
	        return totalCost;
	    }


	    @Override
	    public Cart removeBookFromCart(String bookName, long customerId) throws CartException {
	        Optional<Cart> optionalcart = cartRepository.findById(customerId);

	        if (optionalcart.isEmpty()) {
	            throw new CartException("Cart not found for the given customer");
	        }
	        Cart cart = optionalcart.get();


	        // Find the book by name
	        Book book = bookRepository.findBookByName(bookName);

	        if (book == null) {
	            throw new CartException("Book not found with the given name");
	        }

	        // Remove the book from the cart's book list
	        if (cart.getBookList().remove(book)) {
	            // Update the quantity of the book and total cost
	            updateQuantityAndTotalCost(cart);

	            // Save the updated cart
	            return cartRepository.save(cart);
	        } else {
	            throw new CartException("Book was not found in the cart");
	        }
	    }

	    // this will update the quantity and total cost
	    private void updateQuantityAndTotalCost(Cart cart) {
	        int quantity = cart.getBookList().size();
	        double totalCost = calculateTotalCost(cart);

	        cart.setQuantity(quantity);
	        cart.setTotalCost(totalCost);
	    }


	    // total cost
	    private double calculateTotalCost(Cart cart) {
	        double totalCost = 0.0;
	        for (Book book : cart.getBookList()) {
	            totalCost += book.getPrice();
	        }
	        return totalCost;
	    }



	    public Optional<Cart> getCartById(long cartId)
	    {
	        Optional<Cart> cart = cartRepository.findById(cartId);

	        return cart;
	    }

	    @Override
	    public List<Cart> getAllCarts() {

	        return this.cartRepository.findAll();
	    }



	    //Check out function
	    @Override
	    public String checkOut(Payment.PaymentMode mode, long cartId) throws PaymentNotFoundException, CustomerException, CartException, BookException {

	        Cart  cart = getCartById(cartId).get();

	        Customer customer = customerService.getCustomerById(cartId).get();
	       
	        
	        bookRepository.saveAll(cart.getBookList());
	        int totalOrderQuantity = cart.getQuantity();
	        for (Book book : cart.getBookList()) {
	            int orderedQuantity = book.getTotalStockOfBooks();
	            if (orderedQuantity > 0) {

	                book.setTotalStockOfBooks(orderedQuantity - 1);
	            } else {
	                throw new BookException("Out of Stock");
	            }
	        }
	        bookRepository.saveAll(cart.getBookList());

	        Payment madePayment = new Payment();

	        if (cart.getQuantity() == 0) {

	            throw new CartException("There are no items for checkout");

	        } else {

	            madePayment.setPaymentMode(mode);

	            if(mode.equals(Payment.PaymentMode.COD))

	                madePayment.setPaymentStatus(Payment.PaymentStatus.PENDING);

	            else

	                madePayment.setPaymentStatus(Payment.PaymentStatus.PAID);

	            madePayment.setAmount(cart.getTotalCost());

	            paymentRepository.save(madePayment);



	            Orders order = new Orders();

	            order.setOrderPrice(cart.getTotalCost());

	           order.setOrderQuantity(cart.getBookList().size());

	            order.setOrderQuantity(cart.getQuantity()); //?

	            order.setOrderDate(LocalDate.now());

	         //   order.setDispatchDate(LocalDate.now());

	            order.setOrderAddress(customer.getAddress());

	            order.setOrderStatus(Orders.OrderStatus.PLACED);

	            order.setPayment(madePayment);

	            order.getBookList().addAll(cart.getBookList());

	            customer.getOrderList().add(order);

	            orderService.addOrder(order);



	            cart.getBookList().clear();

	            cart.setQuantity(0);

	            cart.setTotalCost((double) 0);

	            cartRepository.save(cart);

	            customerRepository.save(customer);



	            return "Thanks for the order";

	        }

	    }

	
}
