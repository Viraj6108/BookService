package com.Book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Payment;
import com.Book.Exception.PaymentNotFoundException;
import com.Book.Repository.CartRepository;
import com.Book.Repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	 @Autowired
	    private PaymentRepository paymentRepository;

	    @Autowired
	    private CartRepository cartRepository;
	    @Override
	    public Payment getPaymentById(long id) throws PaymentNotFoundException {
	        Optional<Payment> payment = paymentRepository.findById(id);
	        if (payment.isPresent()) {
	            return payment.get();
	        } else {
	            throw new PaymentNotFoundException("Payment with Id" + id + "was not found");
	        }
	    }



	    @Override
	    public Payment updatePaymentStatus(long paymentId, Payment.PaymentStatus paymentStatus) throws PaymentNotFoundException {
	        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
	        if (optionalPayment.isEmpty()) {
	            throw new PaymentNotFoundException("Payment not found with ID: " + paymentId);
	        }

	        Payment payment = optionalPayment.get();
	        payment.setPaymentStatus((paymentStatus));
	        return paymentRepository.save(payment);
	    }

	    @Override
	    public List<Payment> getAllPayments() {
	        return paymentRepository.findAll();
	    }


	    // create payment
	    @Override
	    public Payment createPayment(double amount, Payment.PaymentMode paymentMode) {
	        Payment payment = new Payment();
	        payment.setAmount(amount);
	        payment.setPaymentMode(paymentMode);
	        payment.setPaymentStatus(Payment.PaymentStatus.PENDING);
	        return paymentRepository.save(payment);
	    }

}
