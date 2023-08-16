package com.Book.Dto;

import com.Book.Entity.Payment;

public class UpdateStatusPaymentRequest {
    private Payment.PaymentStatus paymentStatus;

	public Payment.PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Payment.PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
	


}
