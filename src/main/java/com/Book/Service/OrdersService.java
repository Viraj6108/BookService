package com.Book.Service;

import java.util.List;

import com.Book.Entity.Orders;
import com.Book.Exception.OrdersException;



public interface OrdersService {

	public Orders cancelOrder(Orders order);

	  public Orders addOrder(Orders orders);

	    public List<Orders> getAllOrders() throws OrdersException;

	    Orders getOrderbyId(int orderId) throws OrdersException;
}
