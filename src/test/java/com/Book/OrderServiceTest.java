package com.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Book;
import com.Book.Entity.Orders;
import com.Book.Exception.OrdersException;
import com.Book.Repository.BookRepository;
import com.Book.Repository.OrdersRepository;
import com.Book.Service.BookServiceImpl;
import com.Book.Service.OrderServiceImpl;

public class OrderServiceTest {

	
	@Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrderServiceImpl ordersService;

    @Mock
    private BookRepository bookRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders_OrdersFound_Success() throws OrdersException {
        // Create a list of orders
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        ordersList.add(new Orders());

        // Mocking necessary methods
        when(ordersRepository.findAll()).thenReturn(ordersList);

        // Call the method
        List<Orders> resultOrdersList = ordersService.getAllOrders();

        // Assertions
        assertNotNull(resultOrdersList);
        assertEquals(2, resultOrdersList.size());
    }

    @Test
    void testGetAllOrders_NoOrdersFound() {
        // Mocking necessary methods
        when(ordersRepository.findAll()).thenReturn(Collections.emptyList());

        // Assertions
        assertThrows(OrdersException.class, () -> ordersService.getAllOrders());
    }
    
   
   

    
}
