package com.Book.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Dto.UpdateStatusPaymentRequest;
import com.Book.Entity.Payment;
import com.Book.Exception.PaymentNotFoundException;
import com.Book.Service.PaymentService;

@RestController
public class PaymentController {

	
	 @Autowired
	    private PaymentService paymentService;


	    @PostMapping("/create")
	    public ResponseEntity<Payment> createPayment (@RequestParam double amount, @RequestParam Payment.PaymentMode paymentMode)
	    {
	        Payment payment = paymentService.createPayment(amount, paymentMode);
	        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
	    }

	    // for updating the payment satatus
	    @PostMapping("/update-status/{paymentId}")
	    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable long paymentId, @RequestBody UpdateStatusPaymentRequest request) throws PaymentNotFoundException {
//	        try {
	            Payment updatedPayment = paymentService.updatePaymentStatus(paymentId, request.getPaymentStatus());
	            return ResponseEntity.ok(updatedPayment);
//	        } catch (PaymentNotFoundException e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	        }
	    }

	    @GetMapping("/getAllpayments")
	    public ResponseEntity<List<Payment>> getAllPayments()
	    {
	        List<Payment> paymentList = paymentService.getAllPayments();

	        if (paymentList.isEmpty())
	        {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }

	        return ResponseEntity.ok(paymentList);
	    }
}
