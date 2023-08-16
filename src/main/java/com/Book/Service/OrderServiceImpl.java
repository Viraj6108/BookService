package com.Book.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Book;
import com.Book.Entity.Orders;
import com.Book.Exception.OrdersException;
import com.Book.Repository.BookRepository;
import com.Book.Repository.OrdersRepository;

@Service
public class OrderServiceImpl implements OrdersService{

	 @Autowired
	    private OrdersRepository ordersRepository;

	    @Autowired
	    private CustomerService customerService;

	    @Autowired
	    private BookService bookService;

	    @Autowired
	    private BookRepository bookRepository;

	    @Override
	    public List<Orders> getAllOrders() throws OrdersException {

	        List<Orders> ordersList = ordersRepository.findAll();
	        if(ordersList.isEmpty())
	        {
	            throw new OrdersException("No orders");
	        }
	        return ordersList;
	    }




	    @Override
	    public Orders cancelOrder(Orders order) {
	    	   if (!order.getOrderStatus().equals(Orders.OrderStatus.CANCLELLED.toString())) {
	    	        List<Book> canceledBooks = order.getBookList();
	    	        for (Book book : canceledBooks) {
	    	            int canceledQuantity = book.getTotalStockOfBooks();
	    	            int newQuantity = canceledQuantity + book.getTotalStockOfBooks(); // Add canceled quantity back
	    	            book.setTotalStockOfBooks(newQuantity);
	    	        }

	    	        // Set order status to "CANCELLED"
	    	        order.setOrderStatus(Orders.OrderStatus.CANCLELLED);

	    	        // Save updated book quantities and order status
	    	        bookRepository.saveAll(canceledBooks);
	    	        ordersRepository.save(order);
	    	    }

	    	    return order;
	    	}

	    @Override
	    public Orders addOrder(Orders orders) {
	        return null;
	    }

	    @Override
	    public Orders getOrderbyId(int orderId) throws OrdersException {
	        Orders order = ordersRepository.findById(orderId).get();
	        if (order == null)
	        {
	            throw new OrdersException("Order with id" + " "+orderId+ " "+ "not found");
	        }
	        return order;
	    }

	
}
