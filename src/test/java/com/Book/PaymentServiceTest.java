package com.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Payment;
import com.Book.Exception.PaymentNotFoundException;
import com.Book.Repository.PaymentRepository;
import com.Book.Service.PaymentService;
import com.Book.Service.PaymentServiceImpl;

public class PaymentServiceTest {

	
	 @InjectMocks
	    private PaymentServiceImpl  paymentService;

	    @Mock
	    private PaymentRepository paymentRepository;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetPaymentById_ValidPaymentId_ReturnsPayment() throws PaymentNotFoundException {
	        // Arrange
	        long validPaymentId = 1;
	        Payment expectedPayment = new Payment(/* Initialize with payment details */);
	        when(paymentRepository.findById(validPaymentId)).thenReturn(Optional.of(expectedPayment));

	        // Act
	        Payment actualPayment = paymentService.getPaymentById(validPaymentId);

	        // Assert
	        assertEquals(expectedPayment, actualPayment);
	    }

	    @Test
	    public void testGetPaymentById_InvalidPaymentId_ThrowsPaymentNotFoundException() {
	        // Arrange
	        long invalidPaymentId = 999; // Assuming this ID does not exist in the database
	        when(paymentRepository.findById(invalidPaymentId)).thenReturn(Optional.empty());

	        // Act & Assert
	        assertThrows(PaymentNotFoundException.class,
	                () -> paymentService.getPaymentById(invalidPaymentId));
	    }

	    
	  

	  
}
