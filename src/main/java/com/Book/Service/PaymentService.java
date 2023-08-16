package com.Book.Service;

import java.util.List;

import com.Book.Entity.Payment;
import com.Book.Exception.PaymentNotFoundException;



public interface PaymentService {


    public Payment createPayment(double amount, Payment.PaymentMode paymentMode);


    Payment getPaymentById(long id) throws PaymentNotFoundException;

    Payment updatePaymentStatus(long paymentId, Payment.PaymentStatus paymentStatus) throws PaymentNotFoundException;

   List<Payment> getAllPayments();
}
