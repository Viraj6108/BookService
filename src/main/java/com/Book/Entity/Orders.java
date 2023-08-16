package com.Book.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private int orderQuantity;
	private double orderPrice;
	private String orderAddress;
	private String orderStatus;
	private LocalDate orderDate;

	@ManyToMany
	@JoinTable(
			name = "order_book",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id") )
	private List<Book> bookList = new ArrayList<>();
	
	@OneToOne
	@JsonIgnore
	private Payment payment ;
	
	public enum OrderStatus {
		PLACED, CANCLELLED , DELIVERED, PROCESSING
	}






	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus.toString();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Orders(int orderId, int orderQuantity, double orderPrice, String orderAddress, String orderStatus, LocalDate orderDate, List<Book> bookList, Payment payment) {
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
		this.orderAddress = orderAddress;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.bookList = bookList;
		this.payment = payment;
	}
	
}
