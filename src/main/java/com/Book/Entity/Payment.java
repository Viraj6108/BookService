package com.Book.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String paymentStatus;
	private String paymentMode;
	private double Amount;
	
	public enum PaymentStatus{
		PENDING, PAID, REVERSED
	}
	
	public enum PaymentMode{
		COD, CARD, ONLINE
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus.toString();
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode.toString();
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}


	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(long id, String paymentStatus, String paymentMode, double amount) {
		super();
		this.id = id;
		this.paymentStatus = paymentStatus;
		this.paymentMode = paymentMode;
		Amount = amount;
	}
}
