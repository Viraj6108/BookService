package com.Book.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Entity.Orders;
import com.Book.Exception.OrdersException;
import com.Book.Service.OrdersService;

@RestController
public class OrderController {

	@Autowired
    private OrdersService orderService;

    @GetMapping("/getOrder")
    public ResponseEntity<Orders> getOrdersById(@RequestParam int orderId) throws OrdersException {
        Orders orders = orderService.getOrderbyId(orderId);
        if(orders == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/getAllorders")
    public ResponseEntity<List<Orders>> getAllOrders() throws OrdersException {
        List<Orders> ordersList = orderService.getAllOrders();
        if(ordersList.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ordersList);
    }
}
