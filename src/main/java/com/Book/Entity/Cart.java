package com.Book.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Cart {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartID;
	
	private int quantity;
	private Double TotalCost;
	
	@ManyToMany

	private List<Book> bookList = new ArrayList<>();


	public Cart(long id,  int quantity,
				Double TotalCost) {
		super();
		this.cartID = id;
		this.quantity = quantity;
		this.TotalCost = TotalCost;
	}


	public long getCartID() {
		return cartID;
	}


	public void setCartID(long cartID) {
		this.cartID = cartID;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Double getTotalCost() {
		return TotalCost;
	}


	public void setTotalCost(Double totalCost) {
		TotalCost = totalCost;
	}


	public List<Book> getBookList() {
		return bookList;
	}


	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}


	public Cart(long cartID, int quantity, Double totalCost, List<Book> bookList) {
		super();
		this.cartID = cartID;
		this.quantity = quantity;
		TotalCost = totalCost;
		this.bookList = bookList;
	}


	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
