package com.Book.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookId;
	
	@NotBlank(message = "Book name is Mandatory")
	private String name;
	
	
	private double price;
	
	private String description;
	
	private Integer totalStockOfBooks;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonIgnore
    private Category category;


	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotalStockOfBooks() {
		return totalStockOfBooks;
	}

	public void setTotalStockOfBooks(Integer totalStockOfBooks) {
		this.totalStockOfBooks = totalStockOfBooks;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public Book(long bookId, String name, double price, String description, Integer totalStockOfBooks) {
		this.bookId = bookId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.totalStockOfBooks = totalStockOfBooks;

	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

}
